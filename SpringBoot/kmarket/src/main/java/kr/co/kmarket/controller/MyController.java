package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.PointVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping("my/coupon")
    public String coupon(){
        return "my/coupon";
    }

    @GetMapping("my/home")
    public String home(Principal principal){
        List<PointVO> pointList = service.selectPoints(principal.getName());
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
