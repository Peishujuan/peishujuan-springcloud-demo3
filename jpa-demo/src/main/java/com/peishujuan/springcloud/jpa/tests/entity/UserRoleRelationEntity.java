package com.peishujuan.springcloud.jpa.tests.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_user_role_relation",catalog = "1708d_senior5", schema = "1708d_senior5")
public class UserRoleRelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;//用户id
    private Integer roleId;//角色id

}
