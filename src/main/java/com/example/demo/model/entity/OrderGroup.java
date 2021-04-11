package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","orderDetailList"})
public class OrderGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType;

    private String revAddress;

    private String revName;

    private String paymentType;

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @ManyToOne
    private User user; //user 엔티티의 mappedBy에 해당하는 변수명과 일치해야함
//    private Long userId; //외래키

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
