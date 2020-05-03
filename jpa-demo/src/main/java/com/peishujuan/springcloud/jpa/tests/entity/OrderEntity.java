package com.peishujuan.springcloud.jpa.tests.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_order", catalog = "1708d_senior5", schema = "1708d_senior5")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderNo;//订单编号

    private Integer userId;//用户id

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;//创建时间

    //订单和用户联合, OneToOne一对一  ,JoinColumn指定关联字段, referencedColumnName当前实体的列名
    //insertable = false,updatable = false 限制更新和修改 记得写
//    @JsonIgnoreProperties("orderList")
//    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
//    @JoinColumn(name = "userId",referencedColumnName = "id",insertable = false,updatable = false)
//    private UserEntity user;

    //多对一
    //JsonIgnoreProperties忽略UserEntity的orderList属性
    @JsonIgnoreProperties("orderList")
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId",referencedColumnName = "id",insertable = false,updatable = false)
    private UserEntity user;


    //根据用户名查询
    //因为username不是order表里的字段 所以要加Transient
    @Transient
    private String username;
}
