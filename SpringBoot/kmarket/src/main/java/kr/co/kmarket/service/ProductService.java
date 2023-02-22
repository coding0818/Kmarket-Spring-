package kr.co.kmarket.service;

import javassist.compiler.ast.Keyword;
import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    // 상품번호로 상품 조회하기
    public ProductVO selectProduct(int prodNo){
        return dao.selectProduct(prodNo);
    }

    // cate별 상품리스트 조회
    public List<ProductVO> selectProducts(String cate1, String cate2, int start, String sort, String keyword){
        return dao.selectProducts(cate1, cate2, start, sort, keyword);
    }

    // 상품 네비게이션
    public CateVO selectCate(String cate1, String cate2){
        return dao.selectNavCate(cate1, cate2);
    }

    // 페이징처리
    public int selectCountProduct(String cate1, String cate2, String keyword){
       log.info("keyword : " +keyword);

        return dao.selectCountProduct(cate1, cate2, keyword);
    }

    // 검사 페이징 처리
    //public int selectSearchProduct(String keyword){
    //    return dao.selectSearchProduct(keyword);
    //}

    // 리뷰 리스트 조회
    public List<MyReviewVO> selectReviewListByPaging(int prodNo, int start){
        return dao.selectReviewListByPaging(prodNo, start);
    }

    //리뷰 페이징 처리
    public int selectReviewListCount(int prodNo){
        return dao.selectReviewListCount(prodNo);
    }

    // 장바구니 등록
    public int insertCart(ProductVO vo){
        return dao.insertCart(vo);
    }

    // 장바구니 목록
    public List<ProductVO> selectCart(String uid){
        return dao.selectCart(uid);
    }

    // 장바구니 전체 상품 가격
    public ProductVO selectTotalCart(String uid){
        return dao.selectTotalCart(uid);
    }

    // 장바구니 선택 삭제
    public int deleteCart(List<String> checkList, String uid){

        log.info("checkList : " +checkList);

        return dao.deleteCart(checkList, uid);
    }

    // 장바구니 선택 주문
    public List<ProductVO> selectCartOrder(List<String> checkList, String uid){
        log.info("serCheckList : " + checkList);
        log.info("serUid : " + uid);
        return dao.selectCartOrder(checkList, uid);
    }

    // view에서 주문하기
    public List<ProductVO> selectOrder(List<String> checkList){
        return dao.selectOrder(checkList);
    }

    // 주문하기 등록
    public int insertComplete(OrderVO vo){
        dao.insertComplete(vo);
        return vo.getOrdNo();
    }

    // 주문 상품 등록
    public int insertCompleteItem(Product_OrderItemVO vo){
        log.info("vo : " + vo);
        return dao.insertCompleteItem(vo);
    }

    // 장바구니 주문 완료 상품 삭제
    public int deleteCompleteCart(String uid, List<String> checkList){
        return dao.deleteCompleteCart(uid, checkList);
    }
}
