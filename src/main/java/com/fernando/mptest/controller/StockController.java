package com.fernando.mptest.controller;


import com.fernando.mptest.model.Stock;
import com.fernando.mptest.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/findByName")
    public List<Stock> findAllStock(@RequestParam("name") String stock_name){
        return stockService.findByName(stock_name);
    }

    @GetMapping("/findByCode")
    public List<Stock> findByCode(@RequestParam("code") String code){
        return stockService.findByCode(code);
    }

    @GetMapping("/getHotStock")
    public List<Map<String, Object>> getHotStock(){
        return stockService.getHotStock();
    }

    @GetMapping("/getMostRiseStock")
    public List<Stock> getMostRiseStock(){
        return stockService.getMostRiseStock();
    }
}
