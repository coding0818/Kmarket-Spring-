package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.ProductDAO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

    // 상품 등록 시 파일 업로드
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public ProductVO fileUpload(ProductVO vo){
        // 시스템 경로
        String path = new File(uploadPath).getAbsolutePath();

        return "";
    }

}
