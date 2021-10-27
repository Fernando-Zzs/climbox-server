package com.fernando.mptest.service;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Star;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fernando.mptest.model.Stock;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fernando
 * @since 2021-10-27
 */
public interface IStarService extends IService<Star> {
    public List<Stock> findStarByUserId(String id);

    public boolean deleteById(String user_id, String stock_code);

    public boolean starById(String user_id, String stock_code);
}
