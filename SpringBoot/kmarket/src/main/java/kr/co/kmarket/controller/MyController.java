package kr.co.kmarket.controller;

import kr.co.kmarket.entity.MyPointEntity;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
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
    public String point(Principal principal, Model model, MyPagingVO vo,
                        @RequestParam(value="nowPage", required=false)String nowPage,
                        @RequestParam(value="cntPerPage", required=false)String cntPerPage,
                        @RequestParam(value="division", required = false) int division,
                        @RequestParam(value = "no", required = false) int no,
                        @PageableDefault(size = 10, sort = "pointDate", direction = Sort.Direction.DESC) Pageable pageable){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        // 기간별 조회
        List<MyPointEntity> pointList = null;

        /*
        if(division == 1){
            if(no == 1){
                log.info("findPoint division :"+division);
                log.info("findPoint no:"+no);
                pointList = service.selectPointShort1(principal.getName());
            }else if(no == 2){
                log.info("findPoint division :"+division);
                log.info("findPoint no:"+no);
                pointList = service.selectPointShort2(principal.getName());
            }else{
                log.info("findPoint division :"+division);
                log.info("findPoint no:"+no);
                pointList = service.selectPointShort3(principal.getName());
            }
        }

        log.info("pointList : "+pointList.size());
        */

        // 페이징처리

        int total = service.selectPointListCount(principal.getName());
        if (nowPage == null){
            nowPage = "1";
        }
        if(cntPerPage == null){
            cntPerPage = "10";
        }
        vo = new MyPagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), principal.getName());
        //pointList = service.findByUid(principal.getName(), pageable);

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        model.addAttribute("pointList", pointList);
        model.addAttribute("paging", vo);
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
    public String orderConfirm(String ordNo, int prodNo, int point, Principal principal){
        MyPointVO vo = new MyPointVO();
        vo.setUid(principal.getName());
        vo.setOrdNo(ordNo);
        vo.setPoint(point);
        vo.setPointdes("상품 구매적립");

        log.info("ordNo"+ordNo);
        log.info("point"+point);

        int[] result = service.orderConfirm(ordNo, prodNo, vo);
        return "redirect:/my/home?result="+result[0]+result[1];
    }

}
