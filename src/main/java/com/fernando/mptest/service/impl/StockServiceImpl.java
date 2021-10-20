package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Stock;
import com.fernando.mptest.mapper.StockMapper;
import com.fernando.mptest.service.IStockService;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<Stock> findAllStock() {
        return stockMapper.findAllStock();
    }
}
