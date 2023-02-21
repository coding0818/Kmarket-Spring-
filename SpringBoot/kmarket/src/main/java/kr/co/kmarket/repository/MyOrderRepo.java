package kr.co.kmarket.repository;

import kr.co.kmarket.entity.MyOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepo extends JpaRepository<MyOrderEntity, String> {

    // ordered 페이지
    Page<MyOrderEntity> findByUid(String uid, Pageable pageable);



}
