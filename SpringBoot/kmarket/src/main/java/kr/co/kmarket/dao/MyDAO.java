package kr.co.kmarket.dao;

import kr.co.kmarket.vo.MyCsVO;
import kr.co.kmarket.vo.MyOrderVO;
import kr.co.kmarket.vo.MyReviewVO;
import kr.co.kmarket.vo.MyPointVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyDAO {

    //home
    public List<MyPointVO> selectPoints(String uid);
    public List<MyReviewVO> selectReviews(String uid);
    public List<MyOrderVO> selectOrders(String uid);
    public int updateOrdStatus(@Param("ordNo") int ordNo, @Param("prodNo") int prodNo);
    public List<MyCsVO> selectCs(String uid);
}
