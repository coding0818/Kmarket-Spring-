package kr.co.kmarket.service.admin;
/*
 * 날짜: 2023/02/08
 * 이름: 이원정
 * 내용: 관리자 상품 서비스
 */

import kr.co.kmarket.dao.admin.AdminProdDAO;
import kr.co.kmarket.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@Service
public class AdminProdService {

    @Autowired
    private AdminProdDAO dao;

    // 상품 등록
    public int registerProduct(ProductVO vo, MultipartFile thumb1) throws Exception{

        // 상품 등록하기
        int result = dao.registerProduct(vo);

        // 파일
        //String oName = thumb1.getOriginalFilename();
        //String imgName = "";

        // 시스템 경로
        //String path = System.getProperty("user.dir") + "/src/main/resources/static/files/";

        // 파일명 새로 생성
        //UUID uuid = UUID.randomUUID();
        //String nName = uuid.toString()+oName.substring(oName.lastIndexOf("."));

        //imgName = nName;

        // 파일 저장
        //thumb1.transferTo(new File(path, imgName));
        //ProductVO.builder().thumb1(nName).build();

        return result;

    }
    // 상품 목록
    public List<ProductVO> selectProducts(int start){
        return dao.selectProducts(start);
    }
    // 상품 업데이트
    public int updateProduct(ProductVO vo){
        return dao.updateProduct(vo);
    }
    // 상품 삭제
    public int deleteProduct(int prodNo){
        return dao.deleteProduct(prodNo);
    }







}
