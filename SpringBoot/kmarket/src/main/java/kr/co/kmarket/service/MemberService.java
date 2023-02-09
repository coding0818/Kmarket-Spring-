package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.SellerVO;
import kr.co.kmarket.vo.TermsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    public TermsVO selectTerms(){
        return dao.selectTerms();
    }

    public int insertMember(MemberVO vo){
        vo.setPass(encoder.encode(vo.getPass()));
        return dao.insertMember(vo);
    }

    public int insertSeller(SellerVO vo){
        vo.setPass(encoder.encode(vo.getPass()));
        return dao.insertSeller(vo);
    }
}
