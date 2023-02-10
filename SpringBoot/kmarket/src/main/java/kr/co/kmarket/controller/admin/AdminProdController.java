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
    public String list() {
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
        vo.setSeller("admin");
        vo.setIp(req.getRemoteAddr());

        log.info("here1 : " + vo);

        service.registerProduct(vo);
        return "redirect:/admin/product/register";
    }

    // --------------------------------------2차 카테고리 설정----------------------------------------------
    @ResponseBody
    @GetMapping("admin/product/select")
    public List<CateVO> select(int cate1){

        log.info("here1 : " + cate1);
        List<CateVO> cate2s = inservice.selectCate(cate1);

        log.info("here2 : " + cate2s);

        return cate2s;
    }

}
