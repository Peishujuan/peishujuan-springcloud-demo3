package com.peishujuan.springcloud.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private Integer userType;
    private Date createTime;
}
