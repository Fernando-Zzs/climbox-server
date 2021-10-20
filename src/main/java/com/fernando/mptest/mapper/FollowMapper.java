package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
