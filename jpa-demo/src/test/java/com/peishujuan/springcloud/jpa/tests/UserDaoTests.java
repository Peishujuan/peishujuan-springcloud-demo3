package com.peishujuan.springcloud.jpa.tests;

import com.peishujuan.springcloud.jpa.tests.dao.UserDao;
import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void save(){
        //添加
        UserEntity userEntity = new UserEntity();
        userEntity.setId(5);//用于修改
        userEntity.setUsername("张三33");
        userEntity.setPassword("123456");
        userEntity.setSex(1);
        //新增保存对象后，user已经有主键了
        UserEntity user = userDao.save(userEntity);
        System.out.println(user);

        //修改
        user.setCreated(new Date());
        userDao.save(user);
    }

//    @Test
//    public void findAll(){
//        List<UserEntity> all = userDao.findAll();
//        //System.out.println(all);
//
//        List<UserEntity> username = userDao.findByUsername("%".concat("张").concat("%"));
//        System.out.println(username);
//    }

    @Test
    public void findById(){
        UserEntity byId = userDao.findById(4);
        System.out.println(byId);
    }

    @Test
    public void delete(){
        //如果删除的对象Entity不存在，会报异常
        userDao.deleteById(1);
    }
}
