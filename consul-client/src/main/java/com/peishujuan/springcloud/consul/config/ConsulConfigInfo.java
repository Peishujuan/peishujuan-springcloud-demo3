package com.peishujuan.springcloud.consul.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@JsonIgnoreProperties("$$beanFactory")
@Configuration
@ConfigurationProperties(prefix = "config")
public class ConsulConfigInfo {

    private String name;

    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}