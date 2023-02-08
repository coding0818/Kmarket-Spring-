package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.ProductDAO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductDAO dao;

    // 상품 등록
    public int registerProduct(ProductVO vo){
        int result = dao.registerProduct(vo);
        return result;
    }

}
