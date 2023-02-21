package kr.co.kmarket.dao;

import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDAO {

    // cate별 상품리스트 조회하기
    public List<ProductVO> selectProducts(@Param("cate1") String cate1, @Param("cate2") String cate2, @Param("start") int start, @Param("sort") String sort, @Param("keyword") String keyword);

    // 검색 상품리스트 조회하기
   // public List<ProductVO> selectSearchProducts()

    // 상품번호로 상품 조회하기
    public ProductVO selectProduct(String prodNo);

    // 상품 네비게이션
    public CateVO selectNavCate(@Param("cate1") String cate1, @Param("cate2") String cate2);

    // 페이징처리
    public int selectCountProduct(@Param("cate1") String cate1, @Param("cate2") String cate2, @Param("keyword") String keyword);

    // 검색 페이징처리
    //public int selectSearchProduct(@Param("keyword") String keyword);

    // 장바구니 등록
    public int insertCart(ProductVO vo);

    //장바구니 목록
    public List<ProductVO> selectCart(String uid);

    // 장바구니 전체 상품 가격
    public ProductVO selectTotalCart(@Param("uid") String uid);

    // 장바구니 선택 삭제
    public int deleteCart(@Param("checkList") List<String> checkList, @Param("uid") String uid);

    // 장바구니 선택 주문
    public List<ProductVO> selectCartOrder(@Param("checkList") List<String> checkList, @Param("uid") String uid);

    // 주문하기 등록
    public int insertComplete(OrderVO vo);

    // 주문 상품 삽입
    public int insertCompleteItem(@Param("ordNo") int ordNo, @Param("uid") String uid, @Param("checkList") List<String> checkList);

    // 장바구니 삭제
    public int deleteCompleteCart(@Param("uid") String uid, @Param("checkList") List<String> checkList);
}
