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

        // 파일 ( 각각 thumb1, thumb2, thumb3, detail ) - getOriginalFilename(): 업로드한 파일의 이름 구하기
        String oName1 = vo.getThumb1().getOriginalFilename();
        String oName2 = vo.getThumb2().getOriginalFilename();
        String oName3 = vo.getThumb3().getOriginalFilename();
        String oName4 = vo.getDetail().getOriginalFilename();

        // 시스템 경로 (현재 작업 디렉토리를 얻는 방법(절대 경로) - getProperty(“user.dir”))
        String path = System.getProperty("user.dir") + "/src/main/resources/file";

        // 파일명 새로 생성 ( 각각 thumb1, thumb2, thumb3, detail )
        UUID uuid = UUID.randomUUID();
        String nName1 = uuid.toString()+oName1.substring(oName1.lastIndexOf("."));
        String nName2 = uuid.toString()+oName2.substring(oName2.lastIndexOf("."));
        String nName3 = uuid.toString()+oName3.substring(oName3.lastIndexOf("."));
        String nName4 = uuid.toString()+oName4.substring(oName4.lastIndexOf("."));

        // 파일 저장
        // nName1~4(String) -> vo의 thumb1~3,detail(Multipart)으로 변환
        String imgName1 = "";
        String imgName2 = "";
        String imgName3 = "";
        String imgName4 = "";

        imgName1 = nName1;
        imgName2 = nName2;
        imgName3 = nName3;
        imgName4 = nName4;

        // 저장할 파일, 생성자로 경로와 이름을 지정
        File saveFile1 = new File(path, imgName1);
        File saveFile2 = new File(path, imgName2);
        File saveFile3 = new File(path, imgName3);
        File saveFile4 = new File(path, imgName4);

        // transferTo : 업로드한 파일 데이터를 지정한 파일에 저장
        vo.getThumb1().transferTo(saveFile1);
        vo.getThumb2().transferTo(saveFile2);
        vo.getThumb3().transferTo(saveFile3);
        vo.getDetail().transferTo(saveFile4);

        // 실제 사진은 서버의 특정 위치에 저장하도록 하고 DB에는 사진에 대한 정보만을 저장

        // saveFile1~4(File) -> vo의 thumb1~3,detail(Multipart)으로 변환
        ProductVO.builder()
                .thumb1((MultipartFile) saveFile1)
                .thumb2((MultipartFile) saveFile2)
                .thumb3((MultipartFile) saveFile3)
                .detail((MultipartFile) saveFile4)
                .build();

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
