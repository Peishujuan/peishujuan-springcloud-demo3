package com.peishujuan.springcloud.jpa.tests.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.peishujuan.springcloud.jpa.tests.common.DeleteEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//Entity注解表示和数据库有映射关系
//table注解数据库的表如果和实体类不一致，需要table注解指定表名
//       catalog 和 schema用于设置表所属的数据库名（可跨数据库联查），在当前就不用设置
//Data=Setter 和 Getter 以及 toString
@Data
@Entity
@Table(name = "tb_user",catalog = "1708d_senior5", schema = "1708d_senior5")
@ApiModel("用户的实体类")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2853740643396612597L;
    /** 声明一个实体类的属性映射为数据库的主键列。**/
    @Id
    /** 主键的生成策略，通过strategy 属性指定 IDENTITY、AUTO**/
    /** JPA 自动选择一个最适合底层数据库的主键生成策略**/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //没有注解的属性，在建表时也会生成对应的字段
    @ApiModelProperty("用户的登录名称")
    private String username;
    private String password;
    private Integer sex;
    private String phone;
    //按java命名规范，在表里生成create_time字段
    private Date created;

    //Temporal指定时间精度，因为java中没有具体的时间类型，数据库中要区分date和datetime(默认)
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    private Integer classId;
    //Transient注解-非数据库映射字段，不会在表中生成此字段
    @Transient
    private String className;

    //枚举类
    @Enumerated(EnumType.ORDINAL)
    private DeleteEnum isDelete;

    //大字段lob
    @Lob
    private String content;

    //根据用户查订单
    //JsonIgnoreProperties注解是表示 在根据用户查订单时，忽略根据订单查用户
    //JsonIgnoreProperties忽略OrderEntity的user属性
//    @JsonIgnoreProperties("user")
//    @OneToMany(mappedBy = "userId")
//    private List<OrderEntity> orderList;

    @JsonIgnoreProperties("user")
    @OneToMany
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private List<OrderEntity> orderList;

    //多对多
    //JoinTable是关系表的表名，joinColumns是主表在关系表对应的列，inverseJoinColumns是从表在关系表对应的列
    @JsonIgnoreProperties("userList")
    @ManyToMany
    @JoinTable(name = "tb_user_role_relation", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roleList;

    //根据订单编号查询
    //因为订单编号不是user表里的字段 所以要加Transient
    @Transient
    private String orderNo;




}
