package kr.co.kmarket.dao;

import kr.co.kmarket.vo.MyOrderVO;
import kr.co.kmarket.vo.MyReviewVO;
import kr.co.kmarket.vo.MyPointVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyDAO {

    public List<MyPointVO> selectPoints(String uid);
    public List<MyReviewVO> selectReviews(String uid);
    public List<MyOrderVO> selectOrders(String uid);
}
