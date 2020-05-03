package com.peishujuan.springcloud.jpa.tests;

import com.peishujuan.springcloud.jpa.tests.dao.UserRepository;
import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void save(){
        UserEntity userEntity = new UserEntity();
        //userEntity.setId(7);//用于修改
        userEntity.setUsername("老张");
        userEntity.setPassword("8899");
        userEntity.setPhone("8897321");
        userEntity.setCreated(new Date());
        UserEntity save = userRepository.save(userEntity);
        System.out.println(save);
        save.setSex(2);
        userRepository.save(save);

        //批量保存
        List<UserEntity> list = new ArrayList<>();
        UserEntity u1 = new UserEntity();
        u1.setUsername("老li头er");
        u1.setPassword("123456");
        u1.setPhone("19988887777");
        u1.setCreated(new Date());
        list.add(u1);
        UserEntity u12 = new UserEntity();
        u12.setUsername("老wang头er");
        u12.setPassword("654321");
        u12.setPhone("16655554444");
        u12.setCreated(new Date());
        list.add(u12);
        Iterable<UserEntity> userEntities = userRepository.saveAll(list);
        System.out.println(userEntities);
    }

    //修改
    @Test
    public void update(){
        UserEntity userEntity = userRepository.findById(1).get();
        userEntity.setPhone("13503539365");
        userRepository.save(userEntity);
    }

    //删除
    @Test
    public void delete(){
        //先判断存在否
//        boolean b = userRepository.existsById(6);
//        if(b){
//            userRepository.deleteById(6);
//        }

//        这样写，如果不存在 就会报错
//        boolean a = userRepository.existsById(6);
//        userRepository.deleteById(6);

        //批删时，要先查询对象
        UserEntity entity = userRepository.findById(5).get();
        UserEntity entity1 = userRepository.findById(4).get();
        List<UserEntity> list =new ArrayList<>();
        list.add(entity);
        list.add(entity1);
        userRepository.deleteAll(list);
    }

    //查询
    @Test
    public void query(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterable<UserEntity> allById = userRepository.findAllById(list);
        System.out.println(allById);
    }

    //排序
    @Test
    public void sort(){
        //Sort排序对象，他有很多个静态方法direction来构建；sort
        //单字段排序
        //Sort sort = Sort.by(Sort.Direction.DESC, "id");//按id降序
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        Iterable<UserEntity> all = userRepository.findAll(sort);
        System.out.println(all);
        //多字段排序,构建order
        Sort.Order usernameorder = Sort.Order.desc("username");
        Sort.Order idorder = Sort.Order.desc("id");
        Sort sort1 = Sort.by(usernameorder, idorder);
        Iterable<UserEntity> all1 = userRepository.findAll(sort1);
        System.out.println(all1);
    }

    //分页
    @Test
    public void pageAndSorting(){
        //用PageRequest构建分页的对象，page开始页数，从0开始的，代表第一页
        Pageable pageable = PageRequest.of(0,2);
        Page<UserEntity> page = userRepository.findAll(pageable);
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("总条数"+page.getTotalElements());
        System.out.println("分页数据"+page.getContent());

        //排序分页
        Pageable pageable1 = PageRequest.of(0,2, Sort.Direction.DESC,"id","username");
        Page<UserEntity> all = userRepository.findAll(pageable1);
        System.out.println(all);
    }

    @Test
    public void jpaRepository(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("白白");
        // Example 是一个接口 接口里可以有静态方法
        //他用静态方法来Example 按照Example指定的条件来查询
        //在Example查询的基础上，还可以进行分页和排序
        Example<UserEntity> example = Example.of(userEntity);
        List<UserEntity> all = userRepository.findAll(example);
        System.out.println(all);
    }

    //自定义查询
    @Test
    public void selfquery(){
        //按username
        List<UserEntity> username = userRepository.findByUsername("小张");
        System.out.println(username);
        //模糊
        List<UserEntity> usernameLike = userRepository.findByUsernameLike("%".concat("张").concat("%"));
        System.out.println(usernameLike);
        //模糊 排序
        List<UserEntity> qq = userRepository.findByUsernameLikeOrderByIdDesc("%".concat("张").concat("%"));
        System.out.println(qq);

        //分页从0开始
        Pageable pageable = PageRequest.of(0,2);
        Page<UserEntity> pageInfo = userRepository.findByUsername("张三", pageable);
        System.out.println(pageInfo);
    }

    @Test
    public void testSelfDelete(){
        int i = userRepository.deleteByUsername("小红");
        System.out.println(i);

        int i1 = userRepository.deleteBySexIsNull();
        System.out.println(i1);

        int i2 = userRepository.deleteByUsernameAndSex("张三", 1);
        System.out.println(i2);
    }

    @Test
    public void queryForHSql(){
        List<UserEntity> q1 = userRepository.queryByUsernameAndPassword("小红红", "123456");
        System.out.println(q1);

        List<UserEntity> q2 = userRepository.selectByUsernameAndPassword("小红红", "123456");
        System.out.println(q2);

        List<UserEntity> q3 = userRepository.queryByUsernameAndPasswordParam("小红红", "123456");
        System.out.println(q3);

        Pageable pageable = PageRequest.of(0,3);
        List<UserEntity> q4 = userRepository.queryByUsernameAndPasswordParam("小红红", "123456", pageable);
        System.out.println(q4);
    }

    @Test
    public void updateByQuery(){
        int i = userRepository.updatePasswordById(12, "8236654");
        System.out.println(i);
    }
}

