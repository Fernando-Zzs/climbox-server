package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<<<<<<< Updated upstream
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
=======
import org.apache.ibatis.annotations.*;
>>>>>>> Stashed changes
import org.springframework.stereotype.Repository;

import java.io.Serializable;
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
public interface FollowMapper extends BaseMapper<Follow> {

<<<<<<< Updated upstream
    @Select("select distinct expert_name, expert.expert_id from follow ,expert, deal where user_id = #{id} and follow.expert_id = expert.expert_id and follow.expert_id = deal.expert_id order by trade_time desc;")
    public List<Expert> findFollowByUserId(@Param("id") String id);

    @Delete("delete from follow where user_id = #{user_id} and expert_id = #{expert_id};")
    public int deleteById(@Param("user_id") String user_id, @Param("expert_id") String expert_id);
=======
    @Insert("insert to follow values(#{user_id},#{expert_id})")
    public int followById(@Param("user_id") String user_id,@Param("expert_id") String expert_id);

    @Delete("delete from follow where user_id=#{user_id} and expert_id=#{expert_id}")
    public int unfollowById(@Param("user_id") String user_id,@Param("expert_id") String expert_id);

>>>>>>> Stashed changes
}
