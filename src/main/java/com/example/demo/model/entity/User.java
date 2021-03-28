package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor //모든 매개변수가 들어가는 생성자 생성
@NoArgsConstructor //기본생성자
@Entity
//@Table(name="user") DB 테이블이름과 자바파일 이름이 동일하면 생략가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//어떤식으로 관리할건지 전략설정
    private Long id;
    //@Column(name="account") //변수명이 동일하다면 마찬가지로 생략가능
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
