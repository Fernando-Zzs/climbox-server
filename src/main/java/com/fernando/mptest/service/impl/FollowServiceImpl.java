package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Follow;
import com.fernando.mptest.mapper.FollowMapper;
import com.fernando.mptest.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public List<Map<String, Object>> findFollowByUserId(String id) {
        return followMapper.findFollowByUserId(id);
    }

    @Override
    public boolean deleteById(String user_id, String expert_id) {
        return followMapper.deleteById(user_id, expert_id) > 0;
    }

    @Override
    public boolean followById(String user_id, String expert_id) {
        return followMapper.followById(user_id, expert_id) > 0;
    }

    @Override
    public boolean checkState(String user_id, String expert_id) {
        return followMapper.checkState(user_id, expert_id) > 0;
    }
}
