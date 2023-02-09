package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 날짜: 2023/02/09
 * 이름: 조주영
 * 내용: 고객센터 Article VO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsVO {

    private int csNo;
    private String uid;
    private String cate1;
    private String cate2;
    private String type;
    private String title;
    private String content;
    private String regip;
    private String rdate;
    private int hit;
    private String comment;

    public String getRdate() {
        return rdate.substring(2, 10);
    }
    public String getUid() {return uid.replaceAll("(?<=.{3}).", "*");}
}
