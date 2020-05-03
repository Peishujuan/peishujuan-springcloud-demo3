package com.peishujuan.springcloud.jpa.tests.dao;

import com.peishujuan.springcloud.jpa.tests.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

    @Modifying
    @Transactional
    @Query("update RoleEntity r set r.name=:name where r.id=:id")
    int updateNameById(@Param("id")Integer id, @Param("name")String nme);
}
