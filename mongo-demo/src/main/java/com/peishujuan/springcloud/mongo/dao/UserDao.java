package com.peishujuan.springcloud.mongo.dao;

import com.peishujuan.springcloud.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//主键类型
public interface UserDao extends MongoRepository<User,String> {
    //按用户姓名查 自定义方法查询 和jpa使用规则一样
    List<User> findByUsername(String username);

}
