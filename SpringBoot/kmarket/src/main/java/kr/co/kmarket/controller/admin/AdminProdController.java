package kr.co.kmarket.controller.admin;
/*
 * 날짜: 2023/02/08
 * 이름: 이원정
 * 내용: 관리자 상품 컨트롤러
 */

import kr.co.kmarket.entity.SellerEntity;
import kr.co.kmarket.security.MySellerDetails;
import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.admin.AdminProdService;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminProdController {

    @Autowired
    private AdminProdService service;
    @Autowired
    private IndexService inservice;

    // 상품 리스트 불러오기
    @GetMapping("admin/product/list")
    public String list() {
        return "admin/product/list";
    }

    // 상품 등록하기 '화면'
    @GetMapping("admin/product/register")
    public String register(Model model){
        List<CateVO> cate =inservice.selectCate1();
        model.addAttribute("cate", cate);

        return "admin/product/register";
    }

    // 상품 등록하기
    @PostMapping("admin/product/register")
    public String register(@AuthenticationPrincipal MySellerDetails mySeller, Model model, ProductVO vo, HttpServletRequest req) throws  Exception{
        SellerEntity seller = null;
        if(mySeller != null){ seller = mySeller.getUser(); }
        model.addAttribute("seller", seller);

        vo.setRegip(req.getRemoteAddr());

        service.registerProduct(vo);

        return "admin/product/register";
    }


}
