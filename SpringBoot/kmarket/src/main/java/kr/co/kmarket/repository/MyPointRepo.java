package kr.co.kmarket.repository;

import kr.co.kmarket.entity.MyPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MyPointRepo extends JpaRepository<MyPointEntity, Integer> {

    List<MyPointEntity> findByUid(String uid, Pageable pageable);

}
