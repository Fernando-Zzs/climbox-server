package com.fernando.mptest.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fernando.mptest.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public List<User> findAllUser();

    @Select("SELECT count(*) FROM user WHERE user_name=#{name} and password=#{pwd};")
    public int verifyPassword(@Param("name") String username, @Param("pwd") String password);

    @Insert("insert into user ( user_name, password, phone_num, email, avatar, create_time) values (#{user.username},#{user.password},#{user.phoneNum},#{user.email},#{user.avatar},#{user.createTime});")
    public int insert(@Param("user") User user);

    public User getUserByName(String username);

    @Transactional(rollbackFor = Exception.class)
    public int update(User user);
}
