package com.peishujuan.springcloud.zuul.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 过滤器的配置类
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        //过滤配置
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration= new CorsConfiguration();
        //是否支持安全证书
        corsConfiguration.setAllowCredentials(true);
        //允许来源
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        //允许的头信息
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        //允许的方法get/post
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        //请求的有效期，单位为秒
        corsConfiguration.setMaxAge(3600L);
        //匹配所有请求
        source.registerCorsConfiguration("/**",corsConfiguration);
        //配置跨域过滤器
        CorsFilter corsFilter = new CorsFilter(source);
        //注册过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(corsFilter);
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
