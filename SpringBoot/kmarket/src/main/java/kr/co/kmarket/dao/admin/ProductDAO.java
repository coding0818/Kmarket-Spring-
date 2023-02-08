package kr.co.kmarket.dao.admin;

import kr.co.kmarket.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductDAO {
    // 상품 등록
    public int registerProduct(ProductVO vo);

}
