package kr.co.kmarket.controller.admin;
/*
 * 날짜: 2023/02/08
 * 이름: 이원정
 * 내용: 관리자 상품 컨트롤러
 */

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.admin.AdminProdService;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class AdminProdController {

    @Autowired
    private AdminProdService service;
    @Autowired
    private IndexService inservice;

    // ------------------------------------------ 상품 목록 ------------------------------------------
    @GetMapping("admin/product/list")
    public String list(Model model, String pg, String seller) {

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(seller);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 이후 검색, 레벨에 맞는 상품 보이기 등등 구현
        List<ProductVO> products = service.selectProducts(start);

        System.out.println(products);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);

        return "admin/product/list";
    }

    // ------------------------------------------ 상품 등록하기 '화면' ------------------------------------------
    @GetMapping("admin/product/register")
    public String register(Model model){
        List<CateVO> cates =inservice.selectCate1();
        System.out.println(cates);
        model.addAttribute("cates", cates);

        return "admin/product/register";
    }

    // ------------------------------------------ 상품 등록하기 ------------------------------------------
    @PostMapping("admin/product/register")
    public String register(ProductVO vo, HttpServletRequest req) throws Exception {

        vo.setIp(req.getRemoteAddr());

        log.warn("here1 : " + vo);

        service.registerProduct(vo);

        log.warn("here2 : " + vo);

        return "redirect:/admin/product/register";
    }

    // --------------------------------------2차 카테고리 설정----------------------------------------------
    @ResponseBody
    @GetMapping("admin/product/select")
    public List<CateVO> select(int cate1){
        List<CateVO> cate2s = inservice.selectCate(cate1);
        return cate2s;
    }

}
