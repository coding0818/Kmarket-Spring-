package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.MyOrderVO;
import kr.co.kmarket.vo.MyReviewVO;
import kr.co.kmarket.vo.MyPointVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping("my/coupon")
    public String coupon(){
        return "my/coupon";
    }

    @GetMapping("my/home")
    public String home(Principal principal, Model model){
        List<MyPointVO> pointList = service.selectPoints(principal.getName());
        List<MyReviewVO> reviewList = service.selectReviews(principal.getName());
        List<MyOrderVO> orderList = service.selectOrders(principal.getName());

        log.info("pointList : " + pointList);

        model.addAttribute("pointList", pointList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("orderList", orderList);
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
