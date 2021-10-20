package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Hold;
import com.fernando.mptest.mapper.HoldMapper;
import com.fernando.mptest.service.IHoldService;
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
public class HoldServiceImpl extends ServiceImpl<HoldMapper, Hold> implements IHoldService {

    @Autowired
    private HoldMapper holdMapper;

    @Override
    public List<Hold> findAllHold() {
        return holdMapper.findAllHold();
    }
}
