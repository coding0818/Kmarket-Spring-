package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CsDAO {
    public int insertCsArticle(CsVO vo);
    public CsVO selectCsArticle(int csNo);
    public List<CsVO> selectCsArticles(@Param("cate1") String cate1, @Param("cate2") String cate2, @Param("start") int start);
    public List<CsVO> selectCsArticlesAll(@Param("cate1") String cate1, @Param("start") int start);
    public int updateCsArticle(CsVO vo);
    public int deleteCsArticle(int csNo);

    /*** 기본 CRUD 외 추가 CURD ***/
    
    // 조건에 맞는 전체 CS 게시물 갯수
    public long selectCountTotal(@Param("cate1") String cate1, @Param("cate2") String cate2);
    public long selectCountTotalAll(String cate1);
}
