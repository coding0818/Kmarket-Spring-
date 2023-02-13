package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.MyOrderVO;
import kr.co.kmarket.vo.MyReviewVO;
import kr.co.kmarket.vo.MyPointVO;
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
}
