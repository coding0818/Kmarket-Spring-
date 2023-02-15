package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyDAO dao;

    public List<MyPointVO> selectPoints(String uid){
        return dao.selectPoints(uid);
    }

    public List<MyReviewVO> selectReviews(String uid){
        return dao.selectReviews(uid);
    }

    public List<MyOrderVO> selectOrders(String uid){return dao.selectOrders(uid);}

    public int updateOrdStatus(int ordNo, int prodNo){ return dao.updateOrdStatus(ordNo, prodNo); }

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
}
