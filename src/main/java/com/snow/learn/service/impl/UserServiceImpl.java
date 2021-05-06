package com.snow.learn.service.impl;

import com.snow.learn.entity.User;
import com.snow.learn.mapper.UserMapper;
import com.snow.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User Sel(int id) {
        return userMapper.Sel(id);
    }
}
