package com.fernando.mptest.controller;


import com.alibaba.fastjson.JSONObject;
import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Star;
import com.fernando.mptest.model.Stock;
import com.fernando.mptest.service.IStarService;
import com.fernando.mptest.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fernando
 * @since 2021-10-27
 */
@RestController
@RequestMapping("/star")
public class StarController {
    @Autowired
    private IStarService starService;

    @GetMapping("/findStarByUserId")
    public List<Stock> findStarByUserId(@RequestParam("user_id") String user_id){
        return starService.findStarByUserId(user_id);
    }

    @GetMapping("/deleteById")
    public Object deleteById(@RequestParam("user_id") String user_id, @RequestParam("stock_code") String stock_code){
        JSONObject jsonObject = new JSONObject();

        boolean flag = starService.deleteById(user_id, stock_code);

        if(flag){   //修改成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    @GetMapping("/starById")
    public Object starById(@RequestParam("user_id") String user_id, @RequestParam("stock_code") String stock_code){
        JSONObject jsonObject = new JSONObject();

        boolean flag = starService.starById(user_id, stock_code);

        if(flag){   //修改成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"关注成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"关注失败");
        return jsonObject;
    }

    @GetMapping("/checkState")
    public Object checkState(@RequestParam("user_id") String user_id, @RequestParam("stock_code") String stock_code){
        JSONObject jsonObject = new JSONObject();

        boolean flag = starService.checkState(user_id, stock_code);

        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"未收藏");
        return jsonObject;
    }
}
