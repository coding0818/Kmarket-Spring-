package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @GetMapping("company/index")
    public String index(){
        return "company/index";
    }
}
