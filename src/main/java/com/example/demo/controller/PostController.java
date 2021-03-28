package com.example.demo.controller;

import com.example.demo.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //클래스 리퀘스트매핑이 겹치는 건 상관없음
public class PostController {

    //@RequestMapping(method= RequestMethod.POST,path="/postMethod") 아래와 똑같이 동작
    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        //html form태그 or ajax 비동기 검색에 사용됨. 포스트는 Request Body로 받아야함
        //json,xml,multipart-form/text-plain produces={"application-json"}으로 작성

        return searchParam;
    }
    //postMan같은데서 body에 aacount,email page내용줘서 post해보면 실제 똑같은ㄱ밧을 json으로 받아오는걸확인가능
}

