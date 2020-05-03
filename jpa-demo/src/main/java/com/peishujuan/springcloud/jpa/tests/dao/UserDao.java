package com.peishujuan.springcloud.jpa.tests.dao;

import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository持久层注解
 */
@Repository
public class UserDao {
    @Autowired
    private EntityManager entityManager;

    //新增、修改//Transactional事务注解
    @Transactional
    public UserEntity save(UserEntity userEntity){
        UserEntity user = entityManager.merge(userEntity);
        return user;
    }

    //查询(按主键id查询)
    public UserEntity findById(Integer id){
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        return userEntity;
    }

    //删除(按Id删除)
    //删除对象。先查询，删除的对象是手Jpa管理的对象
    @Transactional
    public void deleteById(Integer id){
        UserEntity userEntity = findById(id);
        entityManager.remove(userEntity);
    }

    //查询所有
//    public List<UserEntity> findAll(){
//        Query query = entityManager.createQuery("from UserEntity u");
//        return query.getResultList();
//    }

    //按用户名查询
//    public List<UserEntity> findByUsername(String username){
//        Query query = entityManager.createQuery("from UserEntity u where u.username like :username");
//        //拼接%，实现模糊查询
//        String likeStr = "%".concat(username).concat("%");
//        query.setParameter("username",likeStr);
//        return query.getResultList();
//    }
}
