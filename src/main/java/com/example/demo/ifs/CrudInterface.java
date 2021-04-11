package com.example.demo.ifs;

import com.example.demo.model.network.Header;

//반드시 작성해야할 메소드들
public interface CrudInterface {
    Header create(); //todo request object 추가
    Header read(Long id);
    Header update();
    Header delete(Long id);
}
