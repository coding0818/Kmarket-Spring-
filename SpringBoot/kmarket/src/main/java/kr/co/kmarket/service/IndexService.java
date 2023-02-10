package kr.co.kmarket.service;

import kr.co.kmarket.dao.IndexDAO;
import kr.co.kmarket.repository.CateRepo;
import kr.co.kmarket.vo.CateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IndexService {

    @Autowired
    private IndexDAO dao;

    @Autowired
    private CateRepo repo;

    // cate
    public List<CateVO> selectCate(int cate1){

        log.info("cate1 : "+cate1);

        return dao.selectCate(cate1);
    }

    public List<CateVO> selectCate1(){
        return dao.selectCate1();
    }

    // index aside 카테고리
    public List<CateVO> selectCategory(){
        return dao.selectCategory();
    }
}
