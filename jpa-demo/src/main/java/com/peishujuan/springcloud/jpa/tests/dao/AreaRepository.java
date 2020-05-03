package com.peishujuan.springcloud.jpa.tests.dao;

import com.peishujuan.springcloud.jpa.tests.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<AreaEntity,Integer> {

    //根据父id查询
    List<AreaEntity> findByPid(Integer pid);
}
