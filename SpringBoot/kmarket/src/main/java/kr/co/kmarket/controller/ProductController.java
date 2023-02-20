package kr.co.kmarket.controller;

import kr.co.kmarket.DTO.PagingDTO;
import kr.co.kmarket.security.MySellerDetails;
import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.SellerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String view(Model model, String prodNo, String cate1, String cate2){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        // 상품번호로 상품 조회하기
        ProductVO  prod = service.selectProduct(prodNo);
        model.addAttribute("prod", prod);

        // 상품 네비게이션
        CateVO ncate = service.selectCate(cate1, cate2);
        model.addAttribute("ncate", ncate);

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


        if(type == "order"){
            // view에서 구매하기
           ProductVO order = (ProductVO) session.getAttribute("order");
           ProductVO prod = service.selectProduct(order.getProdNo());
           order.setProdName(prod.getProdName());
           order.setDescript(prod.getDescript());
           order.setCate1(prod.getCate1());
           order.setCate2(prod.getCate2());
           order.setThumb2(prod.getThumb2());

           List<String> checkList = new ArrayList<>();
           checkList.add(order.getProdNo());
           session.setAttribute("cartCheckList", checkList);

           List<ProductVO> product =service.selectCartOrder(checkList, uid);
            session.setAttribute("complete", product);

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

       log.info("PostcartCheckList : " +checkList);

       Map<String, Integer> map = new HashMap<>();
       map.put("result", 1);
       return map;
    }

    @GetMapping("product/complete")
    public String complete(Model model){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);


        return "product/complete";
    }

    // 주문 처리

}
