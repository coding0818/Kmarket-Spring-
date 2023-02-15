package kr.co.kmarket.dao;

import kr.co.kmarket.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyDAO {

    // 공통
    public int selectCountOrder(String uid);

    //home
    public List<MyPointVO> selectPoints(String uid);
    public List<MyReviewVO> selectReviews(String uid);
    public List<MyOrderVO> selectOrders(String uid);
    public int updateOrdStatus(@Param("ordNo") int ordNo, @Param("prodNo") int prodNo);
    public List<MyCsVO> selectCs(String uid);
    public String selectUserType(String uid);
    public MemberVO selectUser(String uid);
    public SellerVO selectSeller(String uid);

}
