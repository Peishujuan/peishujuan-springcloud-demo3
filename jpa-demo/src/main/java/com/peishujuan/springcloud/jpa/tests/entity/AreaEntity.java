package com.peishujuan.springcloud.jpa.tests.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tb_area",catalog = "1708d_senior5",schema = "1708d_senior5")
public class AreaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;//角色名称

    private Integer pid;//父id

    /*@OneToMany(mappedBy = "pid")
    private List<AreaEntity> areaList;*/

    @Transient
    private List<AreaEntity> areaList;


}
