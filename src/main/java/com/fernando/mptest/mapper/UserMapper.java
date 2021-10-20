package com.fernando.mptest.mapper;

import com.fernando.mptest.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("SELECT * FROM USER WHERE username=#{name} and password=#{pwd};")
    public int verifyPassword(@Param("name") String username, @Param("pwd") String password);

    public int insert(User user);

    public User getUserByName(String username);
}
