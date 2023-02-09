package kr.co.kmarket.security;

import kr.co.kmarket.entity.SellerEntity;
import kr.co.kmarket.repository.SellerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.repository.UserRepo;

@Slf4j
@Service
public class SecurityUserService implements UserDetailsService{

	@Autowired
	private UserRepo repo;

	@Autowired
	private SellerRepo srepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 스프링 시큐리티 인증 동작방식은 아이디/패스워드를 한 번에 조회하는 방식이 아닌
		// 아이디만 이용해서 사용자 정보를 로딩하고 나중에 패스워드를 검증하는 방식

		log.info("SecurityUserService...0 : " + username);

		UserEntity user = null;
		UserDetails myUser = null;

		boolean isPresent = repo.findById(username).isPresent();

		log.info("SecurityUserService...1 : " + isPresent);

		if(isPresent){
			user = repo.findById(username).get();
			log.info("here1 : "+user.getUid());
		}

		if(user == null) {
			// 일반 계정이 없는 경우
			SellerEntity seller = srepo.findById(username).get();

			log.info("here2"+seller.getUid());

			if(seller == null){
				// 판매자 계정도 없는 경우
				log.info("here3");
				throw new UsernameNotFoundException(username);
			}else{
				// 판매자 계정 있는 경우
				log.info("here4");
				myUser = MySellerDetails.builder()
						.user(seller)
						.build();
			}

		}else{
			// security가 세션에 등록하는 객체(빌드 패턴, 빌드 초기화)
			log.info("here5");
			myUser = MyUserDetails.builder()
					.user(user)
					.build();
		}
		log.info("here6");
		
		return myUser;
	}

}
