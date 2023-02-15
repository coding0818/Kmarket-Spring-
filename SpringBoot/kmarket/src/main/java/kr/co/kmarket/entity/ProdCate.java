package kr.co.kmarket.entity;

import java.io.Serializable;
import java.util.Objects;

// 식별자 클래스 (public 사용)
public class ProdCate implements Serializable {

    /*
    (1) 식별자 클래스 속성명 = CateEntity 속성명
    (2) Serializable 인터페이스 적용
    (3) equals, hashCode 구현
     */

    // CateEntity의 cate1, cate2 매핑
    private int cate1;
    private int cate2;

    public ProdCate(){}
    public ProdCate(int cate1, int cate){
        this.cate1=cate1;
        this.cate2=cate2;
    }

    @Override
    public int hashCode(){
        return Objects.hash(cate1, cate2);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        ProdCate other = (ProdCate) obj;
        return cate1 == other.cate1 && cate2 == other.cate2;
    }
}
