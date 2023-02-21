package kr.co.kmarket.controller;

import kr.co.kmarket.entity.MyPointEntity;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

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
        log.info("orderList: "+orderList);

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

    @ResponseBody
    @PostMapping("my/getCompany")
    public Map<String, Object> getCompany(@RequestParam String uid){
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("company", uid);

        log.info(uid);

        service.selectSeller(uid);
        //int result = service.selectSellerInpopup(company);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
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
    public String point(Principal principal, Model model,
                        @RequestParam(value = "division", required = false, defaultValue = "0") int division,
                        @RequestParam(value = "no", required = false, defaultValue = "0") int no,
                        @RequestParam(value = "begin", required = false, defaultValue = "0") String begin,
                        @RequestParam(value = "end", required = false, defaultValue = "0") String end,
                        @PageableDefault(size = 10, sort = "pointDate", direction = Sort.Direction.DESC) Pageable pageable){
        // header part
        int orderCount = service.selectCountOrder(principal.getName());
        int couponCount = service.selectCountCoupon(principal.getName());
        int pointSum = service.selectSumPoint(principal.getName());
        int csCount = service.selectCountCs(principal.getName());

        Page<MyPointEntity> pointList = null;

        // 기간별 조회
        Calendar cal = Calendar.getInstance();
        if(division == 1){
            if(no == 1){
                cal.add(Calendar.DATE, -7);
            }else if(no == 2){
                cal.add(Calendar.DATE, -15);
            }else{
                cal.add(Calendar.MONTH, -1);
            }
                Date date = new Date(cal.getTimeInMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String newdate = sdf.format(date);
                log.info("date : "+date);
                pointList = service.findByUidAndPointDate1(principal.getName(), newdate, pageable);
        }else if(division == 2){
            if(no == 1){
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (no == 2) {
                cal.add(Calendar.MONTH, -2);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (no == 3) {
                cal.add(Calendar.MONTH, -3);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (no == 4) {
                cal.add(Calendar.MONTH, -4);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else{
                cal.add(Calendar.MONTH, -5);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
                Date date = new Date(cal.getTimeInMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sta = new SimpleDateFormat("yyyy-MM-01");
                String startdate = sta.format(date);
                String enddate = sdf.format(date);
                log.info("startdate : "+startdate);
                log.info("enddate : "+enddate);
                pointList = service.findByUidAndPointDate2(principal.getName(), startdate, enddate, pageable);
        }else if(division == 3){
            pointList = service.findByUidAndPointDate2(principal.getName(), begin, end, pageable);
        } else{pointList = service.findByUid(principal.getName(), pageable);}

        // 페이징처리
        int start = (int)(Math.floor(pointList.getNumber() / 5)*5+1);

        log.info("pointList:"+pointList.getContent());
        log.info("pointList:"+pointList.getContent().size());
        log.info("start : "+start);

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("couponCount", couponCount);
        model.addAttribute("pointSum", pointSum);
        model.addAttribute("csCount", csCount);
        model.addAttribute("pointList", pointList.getContent());
        model.addAttribute("page", pointList);
        model.addAttribute("start", start);
        model.addAttribute("division", division);
        model.addAttribute("no", no);

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
