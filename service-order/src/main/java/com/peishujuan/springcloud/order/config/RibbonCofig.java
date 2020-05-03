package com.peishujuan.springcloud.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonCofig {

    /**
     * restTemplate模板发送请求
     * @LoadBalanced此注解实现负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 配置负载均衡
     * @return
     */
    @Bean
    public IRule iRule(){
        //轮询
        //return new RoundRobinRule();
        //随机算法
        return  new RandomRule();
    }
}
