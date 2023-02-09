package kr.co.kmarket.service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.CsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsService {

    @Autowired
    private CsDAO dao;

    /*** cate1에 속하는 모든 CS 게시물을 불러오기 ***/
    public List<CsVO> selectCSArticlesAll (String cate1) {
        return dao.selectCsArticlesAll(cate1);
    }


}
