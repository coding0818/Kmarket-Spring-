package kr.co.kmarket.controller.admin;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.service.admin.ProductService;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private IndexService inservice;

    @GetMapping("admin/product/register")
    public String register(){
        //List<CateVO> cate =inservice.selectCate(cate1);
        //model.addAttribute("cate", cate);

        return "admin/product/register";
    }

    @PostMapping("admin/product/register")
    public String register(ProductVO vo, HttpServletRequest req){
        vo.setRegip(req.getRemoteAddr());
        service.registerProduct(vo);
        return "admin/product/register";
    }

    @GetMapping("admin/product/list")
    public String list() {
        return "/product/list";
    }

    @GetMapping("/product/view")
    public String view() {
        return "/product/view";
    }

    @GetMapping("/product/cart")
    public String cart() {
        return "/product/cart";
    }

    @GetMapping("/product/order")
    public String order() {
        return "/product/order";
    }

    @GetMapping("/product/complete")
    public String complete() {
        return "/product/complete";
    }

}
