package com.fernando.mptest.service;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
public interface IFollowService extends IService<Follow> {
    public List<Map<String, Object>> findFollowByUserId(String id);

    public boolean deleteById(String user_id, String expert_id);

    public boolean followById(String user_id, String expert_id);

    public boolean checkState(String user_id, String expert_id);
}
