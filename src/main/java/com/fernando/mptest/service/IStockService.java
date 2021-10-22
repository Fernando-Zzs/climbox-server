package com.fernando.mptest.service;

import com.fernando.mptest.model.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
public interface IStockService extends IService<Stock> {

    public List<Stock> findAllStock();

    public List<Stock> findByName(String stock_name);

    public List<Stock> findByCode(String code);

    public List<Stock> getHotStock();

    public List<Stock> getMostRiseStock();
}
