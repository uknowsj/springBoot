package com.example.demo.controller;

import com.example.demo.model.SearchParam;
import com.example.demo.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //localhost:8080/api 주소매핑
public class GetController {

    @RequestMapping(method= RequestMethod.GET, path="/getMethod")//localhost:8080/api/getMethod
    public String getRequest(){

        return  "HI GetMethod";
    }
    @GetMapping("/getParameter")//localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id,@RequestParam(name="password") String pwd){
        String password="bbb";
        System.out.println("id : "+id);
        System.out.println("password : "+pwd);

        return id+pwd;
    }
    //파라미터가 길어질 때 객체로 받아서 사용
    @GetMapping("/getMultiParameter")
    public String getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return "okay";
    }

    //네트워크 통신할 때 json형식으로 통신 String이 아닌 SearchParam으로 받으면 됨
    //스프링부트가 잭슨라이브러리내장하고있어서 객체로 리턴하면 알아서 json형태로 리턴

    @GetMapping("/header")
    public Header getHeader(){
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
