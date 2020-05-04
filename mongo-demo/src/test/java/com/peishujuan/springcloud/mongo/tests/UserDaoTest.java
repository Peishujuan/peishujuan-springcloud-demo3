package com.peishujuan.springcloud.mongo.tests;

import com.peishujuan.springcloud.mongo.dao.UserDao;
import com.peishujuan.springcloud.mongo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void save(){
        User user = new User();
        //mongo中主键需要我们自己维护和生成
        user.setId("2");
        user.setUsername("天霸");
        user.setUserType(1);
        user.setCreateTime(new Date());
        User save = userDao.save(user);//使用MongoRepository
        //User save = mongoTemplate.save(user);//使用mongo模板存单值
        System.out.println(save);
        //User sys_user = mongoTemplate.save(user, "sys_user");//使用mongo模板存集合
        //System.out.println(sys_user);
    }

    @Test
    public void query(){
        List<User> list = userDao.findByUsername("巴巴拉拉小魔仙");
        System.out.println(list);
        Pageable pageable = PageRequest.of(1,3);
        Page<User> all = userDao.findAll(pageable);
        System.out.println("分页数据"+all.getContent());
        System.out.println("总页数"+all.getTotalPages());
        System.out.println("总条数"+all.getTotalElements());

        User byId = mongoTemplate.findById("1", User.class, "user");
        System.out.println(byId );
    }

    @Test
    public void del(){
        //userDao.deleteById("5ead3b5f250adbffacb40250");
        User user = new User();
        user.setId("2");
        mongoTemplate.remove(user);
    }
}
