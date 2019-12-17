package com.hao.service.impl;

import com.hao.entity.User;
import com.hao.mapper.UserMapper;
import com.hao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Integer id) throws Exception {
        return userMapper.getUser(id);
    }

}