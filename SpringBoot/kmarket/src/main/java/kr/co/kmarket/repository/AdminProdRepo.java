package kr.co.kmarket.repository;

import kr.co.kmarket.entity.ProdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
// 2023/02/14 이원정 Admin Product JPA Repository
// find 규칙 : https://zara49.tistory.com/130

@Repository
public interface AdminProdRepo extends JpaRepository<ProdEntity, Integer> {
    // <엔티티, PK로 지정한 컬럼의 데이터 타입>

    // findBy(컬럼 이름)Containing : 컬럼에서 키워드가 포함된 것을 찾기
    // 상품명 키워드 검색 + 페이징
    //List<ProdEntity> findProdEntityBySeller(String seller);

    List<ProdEntity> findBySellerAndProdNameContains(String seller, String prodName);

    @Query("select pe from ProdEntity as pe")
    public List<ProdEntity> selectProducts();

}
