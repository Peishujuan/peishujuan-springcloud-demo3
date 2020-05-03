package com.peishujuan.springcloud.order.feign;

import com.peishujuan.springcloud.user.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * UserFeignFallback来实现UserFeignclient
 * feignClient种植钉fallback属性为实现类
 */
@Component
//@Configuration
public class UserFeignFallback implements UserFeignClient{
    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public User getUserByUser(User user) {
        User u = new User();
        u.setUsername("固定de值");
        return u;
    }

    @Override
    public String getUsernameById(Integer id) {
        return null;
    }
}
