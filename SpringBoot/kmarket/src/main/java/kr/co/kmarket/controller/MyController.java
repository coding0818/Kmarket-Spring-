package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("my/coupon")
    public String coupon(){
        return "my/coupon";
    }

    @GetMapping("my/home")
    public String home(){
        return "my/home";
    }

    @GetMapping("my/ordered")
    public String ordered(){
        return "my/ordered";
    }

    @GetMapping("my/point")
    public String point(){
        return "my/point";
    }

    @GetMapping("my/qna")
    public String qna(){
        return "my/qna";
    }

    @GetMapping("my/review")
    public String review(){
        return "my/review";
    }


}
