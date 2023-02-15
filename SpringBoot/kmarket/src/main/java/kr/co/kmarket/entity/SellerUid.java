package kr.co.kmarket.entity;

import java.io.Serializable;
import java.util.Objects;

// 식별자 클래스 (public 사용)
public class SellerUid implements Serializable {

    /*
    (1) 식별자 클래스 속성명 = SellerEntity 속성명
    (2) Serializable 인터페이스 적용
    (3) equals, hashCode 구현
     */

    // SellerEntity의 uid 매핑
    private String uid;

    public SellerUid(){}
    public SellerUid(String uid){
        this.uid = uid;
    }

    @Override
    public int hashCode(){
        return Objects.hash(uid);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SellerUid other = (SellerUid) obj;
        return uid == other.uid;
    }


}
