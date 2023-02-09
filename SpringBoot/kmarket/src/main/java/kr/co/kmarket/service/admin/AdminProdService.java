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

import java.io.File;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class AdminProdService {

    @Autowired
    private AdminProdDAO dao;

    // 상품 등록
    public int registerProduct(ProductVO vo) throws Exception{

        // 상품 등록하기
        int result = dao.registerProduct(vo);

        //

        // 파일 ( 각각 thumb1, thumb2, thumb3, detail )
        String oName1 = vo.getThumb1().getOriginalFilename();
        String oName2 = vo.getThumb2().getOriginalFilename();
        String oName3 = vo.getThumb3().getOriginalFilename();
        String oName4 = vo.getDetail().getOriginalFilename();

        // 시스템 경로 (Java로 현재 작업 디렉토리를 얻는 방법(절대 경로) - getProperty(“user.dir”))
        String path = System.getProperty("user.dir") + "/src/main/resources/file";

        // 파일명 새로 생성 ( 각각 thumb1, thumb2, thumb3, detail )
        UUID uuid = UUID.randomUUID();
        String nName1 = uuid.toString()+oName1.substring(oName1.lastIndexOf("."));
        String nName2 = uuid.toString()+oName2.substring(oName2.lastIndexOf("."));
        String nName3 = uuid.toString()+oName3.substring(oName3.lastIndexOf("."));
        String nName4 = uuid.toString()+oName4.substring(oName4.lastIndexOf("."));

        // 파일 저장
        vo.getThumb1().transferTo(new File(path, nName1));
        vo.getThumb2().transferTo(new File(path, nName2));
        vo.getThumb3().transferTo(new File(path, nName3));
        vo.getDetail().transferTo(new File(path, nName4));

        // 실제 사진은 서버의 특정 위치에 저장하도록 하고 DB에는 사진에 대한 정보만을 저장
        ProductVO.builder().thumb1(nName1).build();

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
