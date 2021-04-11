package com.example.demo.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor //모든 매개변수가 들어가는 생성자 생성
@NoArgsConstructor //기본생성자
@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name="user") DB 테이블이름과 자바파일 이름이 동일하면 생략가능
@ToString(exclude = {"orderGroup"}) //롬복이 해당 유저 클래스에대해 ToString할 때 orderGroup은 제외함. oneToMany나 join시 써줘야함
@Builder
@Accessors(chain=true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//어떤식으로 관리할건지 전략설정
    private Long id;
    //@Column(name="account") //변수명이 동일하다면 마찬가지로 생략가능
    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //User 1: N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")//OneToMany는 fetchType을 걸어줘야함, 매핑할 변수 선택
    private List<OrderGroup> orderGroupList;
}
