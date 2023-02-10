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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class AdminProdService {

    @Autowired
    private AdminProdDAO dao;

    // ------------------------------------------ 상품 등록 ------------------------------------------
    public void registerProduct(ProductVO vo) throws Exception{

        // vo의 thumb1~3 가져오기
        MultipartFile thumb1 = vo.getThumb1();
        MultipartFile thumb2 = vo.getThumb2();
        MultipartFile thumb3 = vo.getThumb3();
        MultipartFile detail = vo.getDetail();

        // 파일 업로드
        ProductVO file = fileUpload(thumb1, thumb2, thumb3, detail, vo);
        vo.setNewThumb1(file.getNewThumb1());
        vo.setNewThumb2(file.getNewThumb2());
        vo.setNewThumb3(file.getNewThumb3());
        vo.setNewDetail(file.getNewDetail());

        // 상품 등록하기
        dao.registerProduct(vo);

    }

    // ------------------------------------------  파일 ----------------------------------------------
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public ProductVO fileUpload(MultipartFile thumb1, MultipartFile thumb2, MultipartFile thumb3, MultipartFile detail, ProductVO vo){

        // 시스템 경로 지정
        String cate1 = vo.getProdCate1();
        String cate2 = vo.getProdCate2();
        String path = new File(uploadPath+cate1+"/"+cate2).getAbsolutePath();

        // 파일 ( 각각 thumb1, thumb2, thumb3, detail ) - getOriginalFilename(): 업로드한 파일의 이름 구하기
        String oName1 = vo.getThumb1().getOriginalFilename();
        String oName2 = vo.getThumb2().getOriginalFilename();
        String oName3 = vo.getThumb3().getOriginalFilename();
        String oName4 = vo.getDetail().getOriginalFilename();

        // 파일명 새로 생성 ( 각각 thumb1, thumb2, thumb3, detail )
        UUID uuid = UUID.randomUUID();
        String newThumb1 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName1.substring(oName1.lastIndexOf("."));
        String newThumb2 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName2.substring(oName2.lastIndexOf("."));
        String newThumb3 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName3.substring(oName3.lastIndexOf("."));
        String newDetail = cate1 + "-" + cate2 + "-" + uuid.toString()+oName4.substring(oName4.lastIndexOf("."));

        // 외부 저장 폴더 자동 생성
        File checkFolder = new File(path);

        if(!checkFolder.exists()){
            try {
                Files.createDirectories(checkFolder.toPath());
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        // 파일 저장
        // transferTo : 업로드한 파일 데이터를 지정한 파일에 저장
        try{
            thumb1.transferTo(new File(path, newThumb1));
            thumb2.transferTo(new File(path, newThumb2));
            thumb3.transferTo(new File(path, newThumb3));
            detail.transferTo(new File(path, newDetail));
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        ProductVO file = new ProductVO();
        file.setNewThumb1(newThumb1);
        file.setNewThumb2(newThumb2);
        file.setNewThumb3(newThumb3);
        file.setNewDetail(newDetail);

        return file;

    }


    // ------------------------------------------ 상품 목록 ------------------------------------------
    public List<ProductVO> selectProducts(String cate1, String cate2, int limitstart){
        return dao.selectProducts(cate1, cate2, limitstart);
    }
    // 상품 업데이트
    public int updateProduct(ProductVO vo){
        return dao.updateProduct(vo);
    }
    // 상품 삭제
    public int deleteProduct(int prodNo){
        return dao.deleteProduct(prodNo);
    }

    // ------------------------------------------ 페이징 ------------------------------------------
    // 페이지 시작값
    public int getLimitStart(int currentPage) {
        return (currentPage - 1) * 10;
    }

    // 현재 페이지
    public int getCurrentPage(String pg) {
        int currentPage = 1;
        if(pg != null) {
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    // 전체 게시글 개수
    public long getTotalCount(String cate1, String cate2) {
        return dao.selectCountTotal(cate1, cate2);
    }

    // 마지막 페이지 번호
    public int getLastPageNum(long total) {

        int lastPage = 0;

        if(total % 10 == 0) {
            lastPage = (int) (total / 10);
        }else {
            lastPage = (int) (total / 10) + 1;
        }

        return lastPage;

    }

    // 각 페이지의 시작 번호
    public int getPageStartNum(long total, int start) {
        return (int) (total - start);
    }

    // 페이지 그룹 1그룹(1~10) 2그룹(11~20)
    public int[] getPageGroup(int currentPage, int lastPage) {
        int groupCurrent = (int) Math.ceil(currentPage/10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if(groupEnd > lastPage) {
            groupEnd = lastPage;
        }

        int[] groups = {groupStart, groupEnd};

        return groups;
    }





}
