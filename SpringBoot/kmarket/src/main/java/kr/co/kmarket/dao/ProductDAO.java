package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDAO {

    // cate별 상품리스트 조회하기
    public List<ProductVO> selectProducts(@Param("cate1") String cate1, @Param("cate2") String cate2, @Param("start") int start);

    // 상품번호로 상품 조회하기
    public ProductVO selectProduct(String prodNo);

    // 상품 네비게이션
    public CateVO selectNavCate(@Param("cate1") String cate1, @Param("cate2") String cate2);

    // 페이징처리
    public int selectCountProduct(@Param("cate1") String cate1, @Param("cate2") String cate2);

    // 장바구니 등록
    public int insertCart(ProductVO vo);

}
