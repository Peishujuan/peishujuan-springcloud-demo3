package com.peishujuan.springcloud.jpa.tests.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_role",catalog = "1708d_senior5", schema = "1708d_senior5")
@EntityListeners(AuditingEntityListener.class)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;//角色名称

    //通过角色查用户
    @JsonIgnoreProperties("roleList")
    @ManyToMany
    @JoinTable(name = "tb_user_role_relation",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userList;

    //创建时间
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)//时间精度
    private Date createTime;

    //修改时间
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)//时间精度
    private Date updateTime;

    //创建人
    @CreatedBy
    private Integer createBy;

    //最后的修改人
    @LastModifiedBy
    private Integer updateBy;
}
