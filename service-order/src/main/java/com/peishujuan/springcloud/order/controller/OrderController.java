package com.peishujuan.springcloud.order.controller;

import com.peishujuan.springcloud.order.feign.UserFeignClient;
import com.peishujuan.springcloud.order.model.Order;
import com.peishujuan.springcloud.order.service.OrderService;
import com.peishujuan.springcloud.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;
    /**
     * 根据Id，查询order
     * @param id
     * @return
     */
    @RequestMapping("getOrderById")
    public Order getOrderById(@RequestParam("id")Integer id){
        Order order = orderService.getOrderById(id);
        //service-user是指定的被调用服务的名称，spring.application.name的名称
        //User user = restTemplate.getForObject("http://service-user/user/getUserById?id="+order.getUserId(), User.class);
        //User user = userFeignClient.getUserById(id);
        /**/
        User userParam = new User();
        userParam.setId(1);
        User user = userFeignClient.getUserByUser(userParam);
        /**/
        log.info("user:{}",user);
        order.setUsername(user.getUsername());
        return order;
    }

}
