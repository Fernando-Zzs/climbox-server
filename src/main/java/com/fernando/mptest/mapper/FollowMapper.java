package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
public interface FollowMapper extends BaseMapper<Follow> {


    @Select("select distinct expert.* from follow ,expert where user_id = #{id} and follow.expert_id = expert.expert_id;")
    public List<Map<String, Object>> findFollowByUserId(@Param("id") String id);

    @Delete("delete from follow where user_id = #{user_id} and expert_id = #{expert_id};")
    public int deleteById(@Param("user_id") String user_id, @Param("expert_id") String expert_id);

    @Insert("insert into follow values(#{user_id},#{expert_id})")
    public int followById(@Param("user_id") String user_id,@Param("expert_id") String expert_id);

    @Delete("delete from follow where user_id = #{user_id} and expert_id = #{expert_id};")
    public int unfollowById(@Param("user_id") String user_id, @Param("expert_id") String expert_id);

    @Select("select count(*) from follow where user_id = #{user_id} and expert_id = #{expert_id};")
    public int checkState(@Param("user_id") String user_id, @Param("expert_id") String expert_id);
}
