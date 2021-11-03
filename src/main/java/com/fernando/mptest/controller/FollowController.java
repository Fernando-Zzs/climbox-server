package com.fernando.mptest.controller;


import com.alibaba.fastjson.JSONObject;
import com.fernando.mptest.model.Expert;
import com.fernando.mptest.service.IFollowService;
import com.fernando.mptest.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private IFollowService followService;

    @GetMapping("/findFollowByUserId")
    public List<Map<String, Object>> findFollowByUserId(@RequestParam("id") String user_id){
        return followService.findFollowByUserId(user_id);
    }

    @GetMapping("/deleteById")
    public Object deleteById(@RequestParam("user_id") String user_id, @RequestParam("expert_id") String expert_id){
        JSONObject jsonObject = new JSONObject();

        boolean flag = followService.deleteById(user_id, expert_id);

        if(flag){   //修改成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    @GetMapping("/followById")
    public Object followById(@RequestParam("user_id") String user_id, @RequestParam("expert_id") String expert_id){
        JSONObject jsonObject = new JSONObject();

        boolean flag = followService.followById(user_id, expert_id);

        if(flag){   //修改成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"关注成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"关注失败");
        return jsonObject;
    }
}
