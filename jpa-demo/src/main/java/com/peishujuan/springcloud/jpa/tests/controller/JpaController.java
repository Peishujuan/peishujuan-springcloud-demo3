package com.peishujuan.springcloud.jpa.tests.controller;

import com.peishujuan.springcloud.jpa.tests.dao.OrderRepository;
import com.peishujuan.springcloud.jpa.tests.dao.RoleRepository;
import com.peishujuan.springcloud.jpa.tests.dao.UserRepository;
import com.peishujuan.springcloud.jpa.tests.entity.OrderEntity;
import com.peishujuan.springcloud.jpa.tests.entity.UserEntity;
import com.peishujuan.springcloud.jpa.tests.service.AreaService;
import com.peishujuan.springcloud.jpa.tests.service.OrderService;
import com.peishujuan.springcloud.jpa.tests.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"JPA测试Controller"})//描述类
@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AreaService areaService;

    //分页
    @ApiOperation(value = "用户分页列表接口",notes = "当前页和分页大小默认值为：1,3")//用在方法上
    @RequestMapping("getPage")
    public Page<UserEntity> getPage(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "3") int pageSize,
                                    UserEntity userEntity){

        //return userRepository.findAll(pageable);

        return userService.getPage(pageNum,pageSize,userEntity);
    }

    @RequestMapping("getOrderAll")
    public Page<OrderEntity> getOrderAll(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "3") int pageSize,
                                         OrderEntity orderEntity){
        return orderService.getOrderAll(pageNum,pageSize,orderEntity);
    }

    @RequestMapping("getRoleAll")
    public Object getRoleAll(){
        return orderRepository.findAll();
    }

    @RequestMapping("getAreaList")
    public Object getAreaList(){
        return areaService.getAreaList();
    }

}
