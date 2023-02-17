package kr.co.kmarket.controller;

import kr.co.kmarket.DTO.PagingDTO;
import kr.co.kmarket.security.MySellerDetails;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
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

    // Index
    @GetMapping("product/list")
    public String list(Model model, String cate1, String cate2, String pg, String sort){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        // 상품 네비게이션
        CateVO ncate = service.selectCate(cate1, cate2);
        model.addAttribute("ncate", ncate);

        // 페이징
        PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectCountProduct(cate1, cate2));

        // cate별 상품리스트 조회하기
        List<ProductVO> products = service.selectProducts(cate1, cate2, paging.getStart(), sort);
        model.addAttribute("prods", products);
        model.addAttribute("paging", paging);
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);

        return "product/list";
    }

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


    @GetMapping("product/cart")
    public String cart(Model model, @AuthenticationPrincipal MySellerDetails mySellerDetails){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        //  장바구니 목록
        List<ProductVO> vo =service.selectCart(mySellerDetails.getUser().getUid());
        model.addAttribute("prod", vo);

        // 장바구니 전체 가격 목록
        ProductVO cvo = service.selectTotalCart(mySellerDetails.getUser().getUid());

        log.info("cvo : "+cvo);

        model.addAttribute("tProd", cvo);

        return "product/cart";
    }

    // 장바구니 등록
    @ResponseBody
    @PostMapping("product/cart")
    public Map<String, Integer> insertCart(ProductVO vo, HttpServletRequest req, @AuthenticationPrincipal MySellerDetails sellerDetails){

        log.info("vo : "+vo);
        log.info("sellerDetails : " +sellerDetails);

        HttpSession session =req.getSession();

        vo.setSeller(sellerDetails.getUser().getUid());

        int result =  service.insertCart(vo);








        Map<String, Integer> map = new HashMap<>();
        map.put("result", result);

        return map;
    }

   // @GetMapping("product/firstorder")
   // public String firstorder(){

    //}


    @GetMapping("product/order")
    public String order(Model model){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        return "product/order";
    }

    @GetMapping("product/complete")
    public String complete(Model model){
        // 카테고리 분류
        Map<String, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);


        return "product/complete";
    }
}
