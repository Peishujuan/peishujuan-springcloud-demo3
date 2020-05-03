package com.peishujuan.springcloud.user.model.service;

import com.peishujuan.springcloud.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     * 根据Id，查询User
     * @param id
     * @return
     */
    public User getUserById(Integer id){
        User user = new User();
        user.setId(id);
        user.setUsername("username"+id);
        return user;
    }

    /**
     * 根据id 查询名称
     * @param id
     * @return
     */
    public String getUsernameById(Integer id){
        User user = getUserById(id);
        return user.getUsername();
    }
}
