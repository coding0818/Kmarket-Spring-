package kr.co.kmarket.entity;
/*
 * 날짜: 2023/02/08
 * 이름: 이원정
 * 내용: 상품 Entity
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="km_product")
public class ProductEntity {

    @Id
    private int prodNo;
    private int cate1;
    private int cate2;
    private String seller;
    private String prodName;
    private String descript;
    private String company;
    private int price;
    private int discount;
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

}
