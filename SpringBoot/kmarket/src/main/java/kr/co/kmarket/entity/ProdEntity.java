package kr.co.kmarket.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="km_product")
public class ProdEntity {

    @Id
    private int prodNo;
    private String cate1;
    private String cate2;
    private String seller;
    private String prodName;
    private String descript;
    private String company;
    private int price;
    private int discount;
    private int sellPrice;
    private int point;
    private int stock;
    private int sold;
    private int delivery;
    private int hit;
    private int score;
    private int review;
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;
    private String duty;
    private String receipt;
    private String bizType;
    private String origin;
    private String ip;
    private String rdate;



    /*
        2023/02/15 이원정 복합 외래키 사용시 JPA 쿼리문 사용
        복합 외래키 사용 시 - 복합키를 pk로 사용한 테이블의 pk를 외래키로 참조할 경우 해당 복합키 모두 참조해야 함.

        방법1 - @IdClass
        방법2 - @Embeddedld

        (1) 부모클래스인 SellerEntity, CateEntity에 @Embeddedld를 적용한 별도의 식별자 클래스 만들기
            - SellerUid
    */

    // 외래키를 기본키로 사용
    //@OneToOne(fetch = FetchType.EAGER)
    //private SellerEntity sellerEntity;

    //@OneToOne(fetch = FetchType.EAGER)
    //private CateEntity cateEntity;

}
