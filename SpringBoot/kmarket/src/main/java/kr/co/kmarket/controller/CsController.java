package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsController {

    @GetMapping("cs/index")
    public String index() {
        return "cs/index";
    }

}
