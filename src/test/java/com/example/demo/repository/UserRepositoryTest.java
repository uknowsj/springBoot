package com.example.demo.repository;

import com.example.demo.StudyApplicationTests;
import com.example.demo.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests{

    //repository 이용해서 crud 테스트라혀면 적어야함
    //Dependency Injection(DI)는 autowired키워드를 통해 주입받음
    @Autowired
    private UserRepository userRepository; //스프링이 직접관리하겠다 new UserRepository 안해도됨

    @Test //test에서 진행할 때 꼭 써줘야함
    public void  create(){
        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1111-2221";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        //생성자 인자 개수와 상관없이 넣을 수 있음
        User u = User.builder().account(account).password(password).status(status).build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        //체인패턴, 객체를 찾은 다음 업데이트 할 때
        //user.setEmail("").setPhoneNumber("");

        //사용자가 어떤 주문바구니를 가지고 있는지
        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("==========주문묶음==========");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());

            System.out.println("==========주문상세==========");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리"+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문상품 : "+orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : "+orderDetail.getStatus());
                System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());


            });
        });
        Assert.assertNotNull(user);
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
    @Transactional//쿼리를 실행해도 마지막에 롤백해줘서 DB에는 영향없음
    public void delete(){
        Optional<User> user = userRepository.findById(1L);
        Assert.assertTrue(user.isPresent()); //반드시 true. 아니면 오류남

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        Assert.assertFalse(deleteUser.isPresent());//반드시 false;
    }
}
