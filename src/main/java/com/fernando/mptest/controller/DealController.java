package com.fernando.mptest.controller;


import com.fernando.mptest.model.Deal;
import com.fernando.mptest.service.IDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/deal")
public class DealController {

    @Autowired
    IDealService dealService;

    @GetMapping("/findDealsByExpertID")
    public List<Deal> findDealsByExpertID(@RequestParam("id") String id){
        return dealService.findDealsByExpertID(id);
    }

}
