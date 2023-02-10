package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.CsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 날짜 : 2023/02/08
 * 이름 : 조주영
 * 내용 : CS 관련 Service
 *
 */


@Service
public class CsService {

    @Autowired
    private CsDAO dao;

    /*** cate1, cate2에 속하는 CS 게시물을 불러오기 ***/
    public List<CsVO> selectCsArticles (String cate1, String cate2) {
        return dao.selectCsArticles(cate1, cate2);
    }

    /*** cate1에 속하는 모든 CS 게시물을 불러오기 ***/
    public List<CsVO> selectCSArticlesAll (String cate1) {
        return dao.selectCsArticlesAll(cate1);
    }


}
