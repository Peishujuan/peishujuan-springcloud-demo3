package com.peishujuan.springcloud.jpa.tests.service;

import com.peishujuan.springcloud.jpa.tests.dao.OrderRepository;
import com.peishujuan.springcloud.jpa.tests.entity.OrderEntity;
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
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<OrderEntity> getOrderAll(@RequestParam("pageNum")Integer pageNum,
                                         @RequestParam("pageSize")Integer pageSize,
                                         OrderEntity orderEntity){
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        Specification<OrderEntity> specification =new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList();
                if(orderEntity.getUsername()!=null){
                    Predicate username = cb.like(root.get("user").get("username"), "%".concat(orderEntity.getUsername()).concat("%"));
                    list.add(username);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return  orderRepository.findAll(specification,pageable);
    }
}
