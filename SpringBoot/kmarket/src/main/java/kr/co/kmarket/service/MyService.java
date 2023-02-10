package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.PointVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyDAO dao;

    public List<PointVO> selectPoints(String uid){
        return dao.selectPoints(uid);
    }
}
