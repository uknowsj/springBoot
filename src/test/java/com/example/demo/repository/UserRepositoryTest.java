package com.example.demo.repository;

import com.example.demo.StudyApplicationTests;
import com.example.demo.model.entity.User;
import org.junit.Assert;
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

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("updated_Account");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
        //id값을 기준으로 변경이 되기 때문에 update에서 setId(3L) 이렇게하면 2번 row가 아닌 3번row에 있는 데이터가 바뀌게 됨
    }

    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(2L);
        Assert.assertTrue(user.isPresent()); //반드시 true. 아니면 오류남

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);
        Assert.assertFalse(deleteUser.isPresent());//반드시 false;
    }
}
