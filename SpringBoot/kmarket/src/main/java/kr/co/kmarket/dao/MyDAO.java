package kr.co.kmarket.dao;

import kr.co.kmarket.vo.PointVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyDAO {

    public List<PointVO> selectPoints(String uid);
}
