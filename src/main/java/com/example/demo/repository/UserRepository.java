package com.example.demo.repository;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> { //User엔티티와 기본키 id는 long type이다

    //핸드폰 번호로 검색
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber); //한 건에 대해 가장 최근 거가 리턴됨
}
