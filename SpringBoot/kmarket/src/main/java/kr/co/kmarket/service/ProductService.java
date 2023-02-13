package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.CateVO;
import kr.co.kmarket.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    // 상품번호로 상품 조회하기
    public ProductVO selectProduct(String prodNo){
        return dao.selectProduct(prodNo);
    }

    // cate별 상품리스트 조회
    public List<ProductVO> selectProducts(String cate1, String cate2){
        return dao.selectProducts(cate1, cate2);
    }

    // 상품 네비게이션
    public CateVO selectCate(String cate1, String cate2){
        return dao.selectNavCate(cate1, cate2);
    }
}
