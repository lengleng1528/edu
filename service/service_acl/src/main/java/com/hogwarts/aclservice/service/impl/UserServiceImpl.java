package com.hogwarts.aclservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hogwarts.aclservice.entity.User;
import com.hogwarts.aclservice.mapper.UserMapper;
import com.hogwarts.aclservice.service.UserService;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
