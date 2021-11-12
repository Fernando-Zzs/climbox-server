package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Expert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fernando.mptest.model.Stock;
import com.fernando.mptest.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

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
public interface ExpertMapper extends BaseMapper<Expert> {

    public List<Expert> findAllExpert();

    @Select("select * from expert where expert_id=#{id}")
    public Expert selectById(@Param("id") String id);

    @Select("select * from expert where expert_name=#{expert_name}")
    public List<Expert> findByName(@Param("expert_name") String expertName);

    @Select("select * from expert order by success_rate")
    public List<Expert> getExpertBySuccessRate();

    @Select("select * from expert order by total_profit_ratio")
    public List<Expert> getExpertByTotalProfitRatio();

    @Select("select * from expert order by follower_num")
    public List<Expert> getExpertByFollowerNum();

    @Select("select expert.*, sum(amount * price) as keyValue\n" +
            "from expert, deal\n" +
            "where expert.expert_id = deal.expert_id\n" +
            "group by expert.expert_id\n" +
            "order by keyValue desc limit 81;")
    public List<Map<String, Object>> getExpertByDealAmount();

    @Update("update expert set follower_num = follower_num + 1 where expert_id=#{expert_id}")
    public int updateFollowNumById(@Param("expert_id")String expert_id);

    @Update("update expert set follower_num = follower_num - 1 where expert_id=#{expert_id}")
    public int decreaseFollowNumById(@Param("expert_id")String expert_id);

    @Select("select * from hold, stock where hold.expert_id=#{expert_id} and hold.code=stock.code order by hold.amount")
    public List<Map<String, Object>> getHoldStocks(@Param("expert_id")String id);

    @Select("select * from deal, stock where deal.expert_id=#{expert_id} and deal.code=stock.code order by deal.trade_time")
    public List<Map<String, Object>> getDeals(@Param("expert_id")String id);
}
