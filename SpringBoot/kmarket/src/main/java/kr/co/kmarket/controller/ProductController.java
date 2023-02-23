package kr.co.kmarket.controller;

import kr.co.kmarket.DTO.PagingDTO;
import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.util.ReviewPagingUtil;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private IndexService iservice;

    @Autowired
    private ProductService service;

    @Autowired
    private MyService myService;

    // Index
    @GetMapping("product/list")
    public String list(Model model, String cate1, String cate2, String pg, String sort, String keyword){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        // 상품 네비게이션
        CateVO ncate = null;
        if(cate1 != null){
            ncate=service.selectCate(cate1, cate2);
        }else{
            if(sort == null){
                sort = "sold";
            }
        }

        model.addAttribute("ncate", ncate);

        // 페이징
        PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectCountProduct(cate1, cate2, keyword));

        log.info("select : " + service.selectCountProduct(cate1, cate2, keyword) );
        log.info("keyword : " +keyword);
        log.info("paging : " +paging);

        // cate별 상품리스트 조회하기
        List<ProductVO> products = service.selectProducts(cate1, cate2, paging.getStart(), sort, keyword);

        log.info("products : " + products);

        model.addAttribute("prods", products);
        model.addAttribute("paging", paging);
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("keyword", keyword);

        return "product/list";
    }

    // 검색 조회
    //public String search(String pg, String keyword){

      //  PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectSearchProduct(keyword));

        //return null;
    //}

    // 단일 상품
    @GetMapping("product/view")
    public String view(Model model, int prodNo, String cate1, String cate2, String pg){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        // 상품번호로 상품 조회하기
        ProductVO  prod = service.selectProduct(prodNo);
        model.addAttribute("prod", prod);

        // 상품 네비게이션
        CateVO ncate = service.selectCate(cate1, cate2);
        model.addAttribute("ncate", ncate);

        // 리뷰 페이징 처리
        PagingDTO revPaging = new ReviewPagingUtil().getPagingDTO(pg, service.selectReviewListCount(prodNo));

        // 리뷰 리스트 조회
        List<MyReviewVO> reviews = service.selectReviewListByPaging(prodNo, revPaging.getStart());


        model.addAttribute("prodNo", prodNo);
        model.addAttribute("revPaging", revPaging);
        model.addAttribute("reviews", reviews);

        return "product/view";
    }

    // 장바구니 목록
    @GetMapping("product/cart")
    public String cart(Model model, Principal principal){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        //  장바구니 목록
        List<ProductVO> prod =service.selectCart(principal.getName());
        model.addAttribute("prod", prod);



        return "product/cart";
    }

    // 장바구니 등록
    @ResponseBody
    @PostMapping("product/cart")
    public Map<String, Integer> insertCart(ProductVO vo, HttpServletRequest req, Principal principal){

        log.info("vo : "+vo);
        log.info("sellerDetails : " +principal);

        HttpSession session =req.getSession();

        vo.setSeller(principal.getName());

        log.info("vo : " +vo.getType());

        int result = 0;

        if(vo.getType().equals("cart")){
            result = service.insertCart(vo);
            session.setAttribute("type", "cart");
        }else if(vo.getType().equals("order")){
            result = 1111;
            session.setAttribute("type", "order");
            session.setAttribute("order", vo);
        }


        Map<String, Integer> map = new HashMap<>();
        map.put("result", result);

        return map;
    }

    // 장바구니 전체 선택 가격
    @ResponseBody
    @PostMapping("product/cart/total")
    public Map<String, ProductVO> selectTotalCart(Principal principal){
        String uid = principal.getName();

        log.info("uid : " + uid);

        // 장바구니 전체 가격 목록
        ProductVO total = service.selectTotalCart(uid);

        log.info("total : "+total);

        Map<String, ProductVO> map = new HashMap<>();
        map.put("result", total);

        return map;
    }

    // 장바구니 선택 삭제
    @ResponseBody
    @PostMapping("product/checkCart")
    public Map<String, Integer> deleteCart(@RequestParam(value = "checkList[]") List<String> checkList, Principal principal){

        String uid = principal.getName();

       int result = service.deleteCart(checkList, uid);

       log.info("result : " +result );

       Map<String, Integer> map = new HashMap<>();
       map.put("result", result);

       return map;
    }


    // 상품 주문
    @GetMapping("product/order")
    public String order(Model model, Principal principal, HttpSession session, HttpServletRequest req){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        String uid = principal.getName();

        // 장바구니, 구매하기에서 넘어온 session
        session = req.getSession();
        String type = (String) session.getAttribute("type");

        log.info("type : " +type);


        if(type.equals("order") ){
            // view에서 구매하기
           ProductVO order = (ProductVO) session.getAttribute("order");
           ProductVO prod = service.selectProduct(order.getProdNo());
           order.setProdName(prod.getProdName());
           order.setDescript(prod.getDescript());
           order.setCate1(prod.getCate1());
           order.setCate2(prod.getCate2());
           order.setThumb2(prod.getThumb2());

           List<String> checkList = new ArrayList<>();
           checkList.add(String.valueOf(order.getProdNo()));
           session.setAttribute("cartCheckList", checkList);

           log.info("orderCheckList : " + checkList);
           log.info("controllUid : " + uid);

           List<ProductVO> product =service.selectOrder(checkList);
            log.info("orderProduct : " + product);
            session.setAttribute("complete", product);

            String userType = myService.selectUserType(principal.getName());

            if(userType.equals("seller")){
                SellerVO seller = myService.selectSeller(principal.getName());
                model.addAttribute("user", seller);
            }else{
                MemberVO user = myService.selectUser(principal.getName());
                model.addAttribute("user", user);
            }

            model.addAttribute("prod", order);
            model.addAttribute("total", order);


        }else if(type.equals("cart")){

            log.info("cart");

            // 장바구니에서 넘어 왔을때

            List<String> checkList = (List<String>) session.getAttribute("cartCheckList");

            log.info("orderCheckList : " + checkList);

            // 장바구니에 상품이 없는 경우
            if(checkList == null) { return "product/cart";}

            // 선택된 상품 조회
            List<ProductVO> prod = service.selectCartOrder(checkList, uid);
            log.info("cartProduct : " + prod);
            session.setAttribute("complete", prod);

            // 상품 총합 계산
            ProductVO total = new ProductVO();
            total.setCount(prod.size());
            for(ProductVO vo : prod){
                total.setPrice(total.getPrice() + vo.getPrice());
                total.setDelivery(total.getDelivery() + vo.getDelivery());
                total.setPoint(total.getPoint() + vo.getPoint());
                total.setTotal(total.getTotal() + vo.getTotal());
            }

            String userType = myService.selectUserType(principal.getName());

            log.info("userType : " + userType);

            if(userType.equals("seller")){
                SellerVO seller = myService.selectSeller(principal.getName());
                model.addAttribute("user", seller);

                log.info("seller name : " + principal.getName());
                log.info("seller member : " + seller);

            }else{
                MemberVO user = myService.selectUser(principal.getName());
                model.addAttribute("user", user);

                log.info("user name : " + principal.getName());
                log.info("user member : " + user);
            }

            model.addAttribute("prod", prod);
            model.addAttribute("total", total);

        }

        return "product/order";
    }

    // 장바구니 상품 주문
    @ResponseBody
    @PostMapping("product/order")
    public Map<String, Integer> selectCartOrder(@RequestParam(value = "checkList[]") List<String> checkList, HttpServletRequest req){
       HttpSession session = req.getSession();
       session.setAttribute("cartCheckList", checkList);
       //session.setAttribute("type", "cart");

       log.info("PostcartCheckList : " +checkList);

       Map<String, Integer> map = new HashMap<>();
       map.put("result", 1);
       return map;
    }

    @GetMapping("product/complete")
    public String complete(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();

        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        // 결제 완료 상품 조회

        @SuppressWarnings("unchecked")
        List<Product_OrderItemVO> complete = (List<Product_OrderItemVO>) session.getAttribute("ordComplete");
        log.info("ordComplete : " +complete);


        OrderVO order = (OrderVO) session.getAttribute("finalOrder");
        log.info("finalOrder : " + order);
        if(complete == null) {return "product/cart";}

        model.addAttribute("complete", complete);
        model.addAttribute("order", order);

        return "product/complete";
    }

    // 주문 처리
    @ResponseBody
    @Transactional
    @PostMapping("product/complete")
    public Map<String, Object> insertComplete(OrderVO vo, Principal principal, HttpServletRequest req){

        log.info("vo1 : " + vo);

        HttpSession session = req.getSession();
        //List<ProductVO> product = (List<ProductVO>) session.getAttribute("complete");
        //String prodName=product.get(0).getProdName();
        String uid = principal.getName();
        String type = (String) session.getAttribute("type");

        vo.setUid(uid);

        // 고유번호 ------------------------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String now = sdf.format(new Date());

        Random random = new Random(1000000000);
        int rand = random.nextInt();
        int randOrdNo = rand + Integer.parseInt(now);
        //-------------------------------------------------

        vo.setOrdNo(randOrdNo);

        log.info("randOrdNo : " + vo.getOrdNo());

        session.setAttribute("finalOrder", vo);

        String userType = myService.selectUserType(principal.getName());

        log.info("userType : " + userType);

        if(userType.equals("seller")){
            SellerVO seller = myService.selectSeller(principal.getName());

            int point =seller.getPoint();

            if(vo.getUsedPoint() < 5000) {
                // 사용한 포인트가 5000원 미만일 때
                // usedPoint 값은 적용을 눌러야만 차감된 상태로 넘어옴
                // js에서 비정상 포인트일 때 작동을 막아놓음
                // vo.setOrdTotPrice(vo.getOrdTotPrice()+vo.getUsedPoint());
                vo.setUsedPoint(0);
            }else if(vo.getUsedPoint() > point) {
                // vo.setOrdTotPrice(vo.getOrdTotPrice()+vo.getUsedPoint());
                vo.setUsedPoint(0);
            }

        }else{
            MemberVO user = myService.selectUser(principal.getName());
            int point = user.getPoint();

            if(vo.getUsedPoint() < 5000) {
                // 사용한 포인트가 5000원 미만일 때
                // usedPoint 값은 적용을 눌러야만 차감된 상태로 넘어옴
                // js에서 비정상 포인트일 때 작동을 막아놓음
                // vo.setOrdTotPrice(vo.getOrdTotPrice()+vo.getUsedPoint());
                vo.setUsedPoint(0);
            }else if(vo.getUsedPoint() > point) {
                // vo.setOrdTotPrice(vo.getOrdTotPrice()+vo.getUsedPoint());
                vo.setUsedPoint(0);
            }

        }

        log.info("vo2 : " + vo);

        // product_order insert
        int orderNo = service.insertComplete(vo);

        List<String> checkList = (List<String>) session.getAttribute("cartCheckList");
       log.info("checkList : " + checkList);
       log.info("randOrdNo : " + randOrdNo);
       log.info("uid : " + uid);

        List<Product_OrderItemVO> items = new ArrayList<>();

       for(String prodNo : checkList){
           Product_OrderItemVO itemVO = new Product_OrderItemVO();
           itemVO.setProdNo(prodNo);
           itemVO.setOrdNo(orderNo);
           itemVO.setUid(uid);
           itemVO.setCount(vo.getOrdCount());
           itemVO.setPrice(vo.getOrdPrice());
           itemVO.setDiscount(vo.getOrdDiscount());
           itemVO.setPoint(vo.getUsedPoint());
           itemVO.setDelivery(vo.getOrdDelivery());
           itemVO.setTotal(vo.getOrdTotPrice());

           items.add(itemVO);

           log.info("itemVO : " + itemVO);
           service.insertCompleteItem(itemVO);
       }

        session.setAttribute("ordComplete", items);


        // 장바구니 삭제
        if(type == "cart"){
            service.deleteCompleteCart(uid, checkList);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("result", orderNo);

        return map;
    }
}
