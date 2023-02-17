package kr.co.kmarket.repository;

import kr.co.kmarket.entity.MyPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPointRepo extends JpaRepository<MyPointEntity, Integer> {

    //List<MyPointEntity> findByUid(String uid, Pageable pageable);

}
