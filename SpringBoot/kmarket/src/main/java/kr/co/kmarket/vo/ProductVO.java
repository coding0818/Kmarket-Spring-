package kr.co.kmarket.vo;
/*
* 날짜: 2023/02/08
* 이름: 이원정
* 내용: 상품 VO
* */
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

    private int prodNo;
    private String prodCate1;
    private String prodCate2;
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
    private String regip;
    private String rdate;


    private int sellPrice;
    private int prodnum;
    private int count;
    private int total;
    private String c1Name;
    private String c2Name;
    private int level;
    private String uid;
    private int cartNo;
    private int totalcount;
    private int costPrice;
    private int totalSalePrice;
    private int totalDelivery;
    private int totalPoint;
    private int totalPrice;
    private String content;
    private int rating;



}
