package com.peishujuan.springcloud.order.service;

import com.peishujuan.springcloud.order.mapper.OrderMapper;
import com.peishujuan.springcloud.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    /**
     * 根据Id，查询order
     * @param id
     * @return
     */
    public Order getOrderById(Integer id){
        Order order = new Order();
        order.setId(id);
        order.setOrderno(System.currentTimeMillis()+"");
        order.setUserId(1);
        return order;
    }


    public List findAll() {
        return orderMapper.findAll();
    }
}
