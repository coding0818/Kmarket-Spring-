package kr.co.kmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="km_member_point")
public class MyPointEntity {

    @Id
    private int pointNo;
    private String uid;
    private String ordNo;
    private int point;
    private String pointDate;
    private String pointdes;
    private String expireDate;
}
