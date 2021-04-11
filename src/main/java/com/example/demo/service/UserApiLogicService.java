package com.example.demo.service;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1.Request Data 가져오기
    // 2.User 생성
    // 3. 생성된 데이터 -> UserApiResponse Return
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        return null;
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(UserApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
