package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParam {
    private String account;
    private String email;
    private String page;

    //파라미터를 받기위해서는 getset메소드 필요 code->generate

}
