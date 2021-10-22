package com.fernando.mptest.controller;


import com.fernando.mptest.model.Deal;
import com.fernando.mptest.model.Expert;
import com.fernando.mptest.model.Stock;
import com.fernando.mptest.service.IDealService;
import com.fernando.mptest.service.IExpertService;
import com.fernando.mptest.service.IStockService;
import com.fernando.mptest.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
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
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    private IExpertService expertService;

    @Autowired
    private IDealService dealService;

    @GetMapping("/findAllExpert")
    public List<Expert> findAllExpert(){
        return expertService.findAllExpert();
    }

    @GetMapping("/selectById")
    public Expert selectById(@RequestParam("id")String id){
        return expertService.selectById(id);
    }

    @GetMapping("/findByName")
    public List<Expert> findByName(@RequestParam("name")String expert_name){
        return expertService.findByName(expert_name);
    }

    @GetMapping("/getExpertBySuccessRate")
    public List<Expert> getExpertBySuccessRate(){
        return expertService.getExpertBySuccessRate();
    }

    @GetMapping("/getExpertByFollowerNum")
    public List<Expert> getExpertByFollowerNum(){
        return expertService.getExpertByFollowerNum();
    }

    @GetMapping("/getExpertByTotalProfitRatio")
    public List<Expert> getExpertByTotalProfitRatio(){
        return expertService.getExpertByTotalProfitRatio();
    }

    @GetMapping("/getExpertByDealAmount")
    public List<Expert> getExpertByDealAmount(){
        return expertService.getExpertByDealAmount();
    }

    @GetMapping("/getHoldStocks")
    public List<Object> getHoldStocks(@RequestParam("id")String id){
        return expertService.getHoldStocks(id);
    }

    @GetMapping("/getDeals")
    public List<Object> getDeals(@RequestParam("id")String id){
        return expertService.getDeals(id);
    }

    @GetMapping("/followExpertById")
    public boolean followById(@RequestParam("expert_id") String expert_id, HttpSession session){
        String user_id = (String) session.getAttribute(Consts.ID);
        if(expertService.selectById(expert_id)==null){
            return false;
        }
        return expertService.followById(user_id,expert_id);
    }

    @GetMapping("/unfollowExpertById")
    public boolean unfollowById(@RequestParam("expert_id") String expert_id, HttpSession session){
        String user_id = (String) session.getAttribute(Consts.ID);
        if(expertService.selectById(expert_id)==null){
            return false;
        }
        return expertService.unfollowById(user_id,expert_id);
    }




}
