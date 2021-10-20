package com.fernando.mptest.controller;


import com.fernando.mptest.model.Stock;
import com.fernando.mptest.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping("/findAllStock")
    public List<Stock> findAllStock(){
        return stockService.findAllStock();
    }
}
