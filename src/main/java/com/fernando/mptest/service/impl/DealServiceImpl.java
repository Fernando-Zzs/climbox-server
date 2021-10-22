package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Deal;
import com.fernando.mptest.mapper.DealMapper;
import com.fernando.mptest.service.IDealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DealServiceImpl extends ServiceImpl<DealMapper, Deal> implements IDealService {
    @Autowired
    private DealMapper dealMapper;

    @Override
    public List<Deal> findDealsByExpertID(String id) {
        return dealMapper.findDealsByExpertID(id);
    }
}
