package com.example.demo.service;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.entity.User;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override //사용자 생성
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {//Header 클래스의 Generic을 통해 데이터 지정해서
        // 1.Request Data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2.User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse Return
        return response(newUser); //하단 메소드에 연결
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);
        //user -> userApiResponse return
        return optional.map(user-> response(user)).orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //data 가져옴
        UserApiRequest userApiRequest = request.getData();

        //id -> user데이터 찾고
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user->{
        //update
            user.setAccount(userApiRequest.getAccount())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                return  user;
        })
                .map(user->userRepository.save(user)) //값이 업데이트 된 user를 받아서 userRepository에 save&새로운 user객체 반환
                .map(user->response(user))
                .orElseGet(()->Header.ERROR("데이터 없음"));

        //userApiResponse 만들기
    }

    @Override
    public Header delete(Long id) {
        //id->repository에서 user찾고
        Optional<User> optional = userRepository.findById(id);

        //repository 에서 delete
        return optional.map(user->{
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터없음"));
        //response return

    }

    private Header<UserApiResponse> response(User user){
        //user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data return
        return Header.OK(userApiResponse);
    }
}
