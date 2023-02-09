package kr.co.kmarket.dao;

import kr.co.kmarket.vo.SellerVO;
import kr.co.kmarket.vo.TermsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberDAO {

    public TermsVO selectTerms();

    public int insertSeller(SellerVO vo);
}
