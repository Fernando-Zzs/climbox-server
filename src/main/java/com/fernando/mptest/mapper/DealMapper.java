package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Deal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface DealMapper extends BaseMapper<Deal> {

    @Select("select * from deal where expert_id=#{expert_id}")
    public List<Deal> findDealsByExpertID(@Param("expert_id") String id);

}
