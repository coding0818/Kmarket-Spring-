package kr.co.kmarket.repository;

import kr.co.kmarket.entity.SellerEntity;
import kr.co.kmarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<SellerEntity, String> {
}
