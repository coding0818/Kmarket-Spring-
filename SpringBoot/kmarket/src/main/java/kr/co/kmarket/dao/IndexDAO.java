package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CateVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface IndexDAO {

    public List<CateVO> selectCate(int cate1);
}
