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

    // ------------------------------------------  파일 ----------------------------------------------
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // ------------------------------------------ 상품 등록 ------------------------------------------
    public int registerProduct(ProductVO vo) throws Exception{

        // 상품 등록하기
        int result = dao.registerProduct(vo);

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
        String nName1 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName1.substring(oName1.lastIndexOf("."));
        String nName2 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName2.substring(oName2.lastIndexOf("."));
        String nName3 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName3.substring(oName3.lastIndexOf("."));
        String nName4 = cate1 + "-" + cate2 + "-" + uuid.toString()+oName4.substring(oName4.lastIndexOf("."));

        // file 폴더 생성
        File checkFolder = new File(path);
        if(!checkFolder.exists()){
            try {
                Files.createDirectories(checkFolder.toPath());
            }catch (IOException e){
                e.printStackTrace();
            }

        }

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
        try{
            vo.getThumb1().transferTo(saveFile1);
            vo.getThumb2().transferTo(saveFile2);
            vo.getThumb3().transferTo(saveFile3);
            vo.getDetail().transferTo(saveFile4);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;

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
