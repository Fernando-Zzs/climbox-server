package com.fernando.mptest.controller;


import com.fernando.mptest.model.Hold;
import com.fernando.mptest.service.IHoldService;
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
@RequestMapping("/hold")
public class HoldController {
    @Autowired
    private IHoldService holdService;

    @GetMapping("/findAllHold")
    public List<Hold> findAllHold(){ return holdService.findAllHold(); }
}
