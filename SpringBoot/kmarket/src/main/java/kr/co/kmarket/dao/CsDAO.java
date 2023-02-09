package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CsDAO {
    public int insertCsArticle(CsVO vo);
    public CsVO selectCsArticle(int csNo);
    public List<CsVO> selectCsArticles(@Param("cate1") String cate1, @Param("cate2") String cate2);
    public List<CsVO> selectCsArticlesAll(String cate1);
    public int updateCsArticle(CsVO vo);
    public int deleteCsArticle(int csNo);
}
