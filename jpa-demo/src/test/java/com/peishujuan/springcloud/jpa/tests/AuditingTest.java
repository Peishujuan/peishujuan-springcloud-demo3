package com.peishujuan.springcloud.jpa.tests;

import com.peishujuan.springcloud.jpa.tests.dao.RoleRepository;
import com.peishujuan.springcloud.jpa.tests.entity.RoleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditingTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test(){
        //添加
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("项目经理");
        RoleEntity save = roleRepository.save(roleEntity);

        //修改
        roleEntity.setId(4);//修改
        roleRepository.updateNameById(save.getId(),"产品经理");
    }
}
