package com.example.demo.repository;

import com.example.demo.StudyApplicationTests;
import com.example.demo.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests{

    //repository 이용해서 crud 테스트라혀면 적어야함
    //Dependency Injection(DI)는 autowired키워드를 통해 주입받음
    @Autowired
    private UserRepository userRepository; //스프링이 직접관리하겠다 new UserRepository 안해도됨

    @Test //test에서 진행할 때 꼭 써줘야함
    public void  create(){
        //Strin sql = "INSERT INTO user(%s,%s,%d) values (account,email,age);
        //jpa는 오브젝트를 가지고 데이터베이스를 관리할수있게 도와줌
        User user = new User(); //di핵심은 싱글톤?
        user.setAccount("TestUser02"); //not null
        user.setEmail("TestUser02@gmail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser02");

        User newUser = userRepository.save(user); //user를 넘기고 DB에 저장된 새user객체를 리턴받음
        System.out.println("newUser: "+newUser);

    }
    
    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);

        //찾는user가 있으면 실행
        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email: "+selectUser.getEmail());
        });
    }
    public void update(){}
    public void delete(){}
}
