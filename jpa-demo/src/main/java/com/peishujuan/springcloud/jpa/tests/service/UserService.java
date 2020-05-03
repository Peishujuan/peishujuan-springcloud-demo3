package com.peishujuan.springcloud.jpa.tests.service;

import com.peishujuan.springcloud.jpa.tests.dao.UserDao;
import com.peishujuan.springcloud.jpa.tests.dao.UserRepository;
import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> getPage(@RequestParam("pageNum")Integer pageNum,
                                    @RequestParam("pageSize")Integer pageSize,
                                    UserEntity userEntity){
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);

        //继承了JpaSpecificationExecutor后的新方法,
        //构建Specification
        Specification<UserEntity> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                //保存多个查询条件
                List<Predicate> list = new ArrayList<>();
                //模糊查询  查询的字段不为空时，添加条件
                if(userEntity.getUsername()!=null){
                    //构建条件
                    Predicate username = cb.like(root.get("username"), "%".concat(userEntity.getUsername()).concat("%"));
                    //添加到list
                    list.add(username);
                }
                //按orderNo模糊查询
                if ((userEntity.getOrderNo()!=null)){
                    //user连接查询order
                    Predicate orderNo = cb.like(root.join("orderList").get("orderNo"), "%".concat(userEntity.getOrderNo()).concat("%"));
                    list.add(orderNo);
                }
                //返回的多条件查询结果
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return userRepository.findAll(specification,pageable);
    }
}
