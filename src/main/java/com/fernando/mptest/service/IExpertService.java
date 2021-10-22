package com.fernando.mptest.service;

import com.fernando.mptest.model.Expert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fernando.mptest.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
public interface IExpertService extends IService<Expert> {
    public List<Expert> findAllExpert();
    public Expert selectById(String id);
    public List<Expert> findByName(String expertName);
    public List<Expert> getExpertBySuccessRate();
    public List<Expert> getExpertByTotalProfitRatio();
    public List<Expert> getExpertByFollowerNum();
    public List<Expert> getExpertByDealAmount();

    public boolean followById(String user_id, String expert_id);
    public boolean unfollowById(String user_id, String expert_id);

    public List<Object> getHoldStocks(String id);

    public List<Object> getDeals(String id);
}
