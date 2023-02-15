package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.*;
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
    public String coupon(Principal principal, Model model){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        return "my/coupon";
    }

    @GetMapping("my/home")
    public String home(Principal principal, Model model){
        List<MyPointVO> pointList = service.selectPoints(principal.getName());
        List<MyReviewVO> reviewList = service.selectReviews(principal.getName());
        List<MyOrderVO> orderList = service.selectOrders(principal.getName());
        List<MyCsVO> csList = service.selectCs(principal.getName());

        String userType = service.selectUserType(principal.getName());

        log.info("userType : " + userType);

        if(userType.equals("seller")){
            SellerVO seller = service.selectSeller(principal.getName());
            model.addAttribute("member", seller);

            log.info("seller name : " + principal.getName());
            log.info("seller member : " + seller);

        }else{
            MemberVO user = service.selectUser(principal.getName());
            model.addAttribute("member", user);

            log.info("user name : " + principal.getName());
            log.info("user member : " + user);
        }

        log.info("pointList : " + pointList);

        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("pointList", pointList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("csList", csList);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        return "my/home";
    }

    @GetMapping("my/ordered")
    public String ordered(Principal principal, Model model){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        return "my/ordered";
    }

    @GetMapping("my/point")
    public String point(Principal principal, Model model){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);

        return "my/point";
    }

    @GetMapping("my/qna")
    public String qna(Principal principal, Model model){

        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);

        return "my/qna";
    }

    @GetMapping("my/review")
    public String review(Principal principal, Model model){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        return "my/review";
    }

    @GetMapping("my/info")
    public String info(Principal principal, Model model){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        return "my/info";
    }

    @GetMapping("my/orderConfirm")
    public String orderConfirm(int ordNo, int prodNo){
        int result = service.updateOrdStatus(ordNo, prodNo);
        return "redirect:/my/home?result="+result;
    }


}
