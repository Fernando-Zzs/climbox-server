package com.fernando.mptest.service.impl;

import com.fernando.mptest.mapper.FollowMapper;
import com.fernando.mptest.mapper.UserMapper;
import com.fernando.mptest.model.Expert;
import com.fernando.mptest.mapper.ExpertMapper;
import com.fernando.mptest.model.User;
import com.fernando.mptest.service.IExpertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements IExpertService {

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Expert> findAllExpert() {
        return expertMapper.findAllExpert();
    }

    @Override
    public Expert selectById(String id) {
        return expertMapper.selectById(id);
    }

    @Override
    public List<Expert> findByName(String expertName) {
        return expertMapper.findByName(expertName);
    }

    @Override
    public List<Expert> getExpertBySuccessRate() {
        return expertMapper.getExpertBySuccessRate();
    }

    @Override
    public List<Expert> getExpertByTotalProfitRatio() {
        return expertMapper.getExpertByTotalProfitRatio();
    }

    @Override
    public List<Expert> getExpertByFollowerNum() {
        return expertMapper.getExpertByFollowerNum();
    }

    @Override
    public List<Expert> getExpertByDealAmount() {
        return expertMapper.getExpertByDealAmount();
    }

    @Override
    @Transactional
    public boolean followById(String user_id, String expert_id) {
        return (followMapper.followById(user_id,expert_id)>0)&&(expertMapper.updateFollowNumById(expert_id)>0);
    }

    @Override
    @Transactional
    public boolean unfollowById(String user_id, String expert_id) {
        return (followMapper.unfollowById(user_id,expert_id)>0)&&(expertMapper.decreaseFollowNumById(expert_id)>0);
    }

    @Override
    public List<Object> getHoldStocks(String id) {
        return expertMapper.getHoldStocks(id);
    }

    @Override
    public List<Object> getDeals(String id) {
        return expertMapper.getDeals(id);
    }
}
