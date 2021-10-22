package com.fernando.mptest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fernando.mptest.model.User;
import com.fernando.mptest.mapper.UserMapper;
import com.fernando.mptest.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        return userMapper.verifyPassword(username,password) > 0;
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user) > 0;
    }
}
