package com.fernando.mptest.service;

import com.fernando.mptest.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
public interface IUserService extends IService<User> {

    public List<User> findAllUser();

    public boolean verifyPassword(String username, String password);

    public boolean insert(User user);

    public User getUserByName(String username);
}
