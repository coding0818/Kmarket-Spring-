package kr.co.kmarket.controller.admin;

import kr.co.kmarket.entity.SellerEntity;
import kr.co.kmarket.security.MySellerDetails;
import kr.co.kmarket.service.CsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private CsService service;

    @GetMapping("admin/index")
    public String index(@AuthenticationPrincipal MySellerDetails myUser, Model model){

        SellerEntity seller = myUser.getUser();

        model.addAttribute("seller", seller);
        return "admin/index";
    }

    @GetMapping("admin/cs/delete")
    public String csDelete(String cate1, int csNo) {
        service.deleteCsArticle(csNo);
        return "redirect:/admin/cs/"+cate1+"/list";
    }



}
