package kr.co.kmarket.repository;

import kr.co.kmarket.entity.MyPointEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyPointRepo extends JpaRepository<MyPointEntity, Integer> {

    // point 페이지

    Page<MyPointEntity> findByUid(String uid, Pageable pageable);

    @Query(value="select pe from MyPointEntity pe where pe.uid=:uid AND (pe.pointDate > :date) order by pe.pointDate desc")
    Page<MyPointEntity> findByUidAndPointDate1(@Param("uid") String uid, @Param("date") String date, Pageable pageable);

    @Query(value="select pe from MyPointEntity pe where pe.uid=:uid AND (pe.pointDate > :startdate) AND (pe.pointDate < :enddate) order by pe.pointDate desc")
    Page<MyPointEntity> findByUidAndPointDate2(@Param("uid") String uid, @Param("startdate") String startdate, @Param("enddate") String enddate, Pageable pageable);

    // ordered 페이지
    /*
    @Query(value="SELECT a.*, b.*, c.prodName, c.company, c.thumb1, c.cate1, c.cate2 FROM `km_product_order_item` AS a JOIN `km_product_order` AS b ON a.ordNo=b.ordNo JOIN `km_product` AS c ON a.prodNo=c.prodNo WHERE b.uid=#{uid} ORDER BY b.`ordDate` DESC LIMIT 10;")
    Page<MyPointEntity> findByUid2(String uid, Pageable pageable);

    @Query(value="SELECT a.*, b.*, c.prodName, c.company, c.thumb1, c.cate1, c.cate2 FROM `km_product_order_item` AS a JOIN `km_product_order` AS b ON a.ordNo=b.ordNo JOIN `km_product` AS c ON a.prodNo=c.prodNo WHERE b.uid=#{uid} ORDER BY b.`ordDate` DESC LIMIT 5;")
    Page<MyPointEntity> findByUidAndPointDate3();
    */
}
