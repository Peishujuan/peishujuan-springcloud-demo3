package com.peishujuan.springcloud.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private Integer id;
    private String orderno;
    private Integer UserId;
    private String username;
}
