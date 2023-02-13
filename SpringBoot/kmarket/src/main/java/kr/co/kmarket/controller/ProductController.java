package kr.co.kmarket.controller;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.vo.CateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private IndexService iservice;

    @GetMapping("product/list")
    public String list(Model model){
        // 카테고리 분류
        Map<Integer, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);


        return "product/list";
    }

    @GetMapping("product/view")
    public String view(Model model){
        // 카테고리 분류
        Map<Integer, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        return "product/view";
    }

    @GetMapping("product/cart")
    public String cart(Model model){
        // 카테고리 분류
        Map<Integer, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        return "product/cart";
    }

    @GetMapping("product/order")
    public String order(Model model){
        // 카테고리 분류
        Map<Integer, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);

        return "product/order";
    }

    @GetMapping("product/complete")
    public String complete(Model model){
        // 카테고리 분류
        Map<Integer, List<CateVO>> cate = iservice.selectCates();
        model.addAttribute("cate", cate);


        return "product/complete";
    }
}
