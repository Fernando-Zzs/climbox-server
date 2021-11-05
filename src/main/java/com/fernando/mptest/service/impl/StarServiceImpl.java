package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Star;
import com.fernando.mptest.mapper.StarMapper;
import com.fernando.mptest.model.Stock;
import com.fernando.mptest.service.IStarService;
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
 * @since 2021-10-27
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements IStarService {

    @Autowired
    private StarMapper starMapper;

    @Override
    public List<Stock> findStarByUserId(String id) {
        return starMapper.findStarByUserId(id);
    }

    @Override
    public boolean deleteById(String user_id, String stock_code) {
        return starMapper.deleteById(user_id, stock_code)>0;
    }

    @Override
    public boolean starById(String user_id, String stock_code) {
        return starMapper.followById(user_id, stock_code)>0;
    }

    @Override
    public boolean checkState(String user_id, String stock_code) {
        return starMapper.checkState(user_id, stock_code) > 0;
    }
}
