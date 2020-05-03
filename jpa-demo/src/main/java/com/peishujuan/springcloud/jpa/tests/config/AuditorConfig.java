package com.peishujuan.springcloud.jpa.tests.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorConfig implements AuditorAware<Integer> {

    @Override               // 获得当前用户的
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(1);
    }
}
