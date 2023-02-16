package kr.co.kmarket.service;

import kr.co.kmarket.vo.MyPagingVO;
import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyDAO dao;

    // 공통
    public int selectCountOrder(String uid){
        return dao.selectCountOrder(uid);
    }

    public int selectCountCoupon(String uid){
        return dao.selectCountCoupon(uid);
    }

    public int selectSumPoint(String uid){
        return dao.selectSumPoint(uid);
    }

    public int selectCountCs(String uid){
        return dao.selectCountCs(uid);
    }

    // home
    public List<MyPointVO> selectPoints(String uid){
        return dao.selectPoints(uid);
    }

    public List<MyReviewVO> selectReviews(String uid){
        return dao.selectReviews(uid);
    }

    public List<MyOrderVO> selectOrders(String uid){return dao.selectOrders(uid);}

    @Transactional
    public int[] orderConfirm(String ordNo, int prodNo, MyPointVO vo){
        int result1 = dao.insertPoint(vo);
        int result2 = dao.updateOrdStatus(ordNo, prodNo);
        int result[] = {result1, result2};
        return result;
    }

    public List<MyCsVO> selectCs(String uid){
        return dao.selectCs(uid);
    }

    public String selectUserType(String uid){
        return dao.selectUserType(uid);
    }

    public MemberVO selectUser(String uid){
        return dao.selectUser(uid);
    }
    public SellerVO selectSeller(String uid){
        return dao.selectSeller(uid);
    }

    // point
    public int selectPointListCount(String uid){
        return dao.selectPointListCount(uid);
    }

    public List<MyPointVO> selectPointListByPaging(MyPagingVO vo){
        return dao.selectPointListByPaging(vo);
    }
}
