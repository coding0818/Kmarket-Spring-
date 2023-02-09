package kr.co.kmarket.dao;

import kr.co.kmarket.vo.TermsVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberDAO {

    public TermsVO selectTerms();
}
