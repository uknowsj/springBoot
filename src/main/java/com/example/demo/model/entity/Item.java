package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql은 Identity로
    private Long id;

    private String name;

    private Integer price;

    private String content;


    //1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item") //oderdetail에있는 item에 매칭
    private List<OrderDetail> orderDetailList;
}
