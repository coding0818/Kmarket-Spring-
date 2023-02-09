package kr.co.kmarket.dao.admin;
/*
 * 날짜: 2023/02/08
 * 이름: 이원정
 * 내용: 관리자 상품 DAO
 */
import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminProdDAO {
    // 상품 등록
    public int registerProduct(ProductVO vo);
    // 상품 목록
    public List<ProductVO> selectProducts(int start);
    // 상품 업데이트
    public int updateProduct(ProductVO vo);
    // 상품 삭제
    public int deleteProduct(int prodNo);




}
