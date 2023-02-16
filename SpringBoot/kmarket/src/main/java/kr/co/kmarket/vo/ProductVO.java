package kr.co.kmarket.vo;
/*
* 날짜: 2023/02/08
* 이름: 이원정
* 내용: 상품 VO
* */
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductVO {

    private String cate1;
    private String cate2;
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
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
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

    // 추가
    private String newThumb1;
    private String newThumb2;
    private String newThumb3;
    private String newDetail;

    // 필요 여부 판단 후 삭제 해 주십시오
    private String prodNo;
    private String type;
    private int sellPrice;
//    private int prodnum;
    private int count;
    private int total;
//    private String c1Name;
//    private String c2Name;
    private int level;


//    private String uid;
//    private int cartNo;
//    private int totalcount;
//    private int costPrice;
//    private int totalSalePrice;
//    private int totalDelivery;
//    private int totalPoint;
//    private int totalPrice;
//    private String content;
//    private int rating;



}
