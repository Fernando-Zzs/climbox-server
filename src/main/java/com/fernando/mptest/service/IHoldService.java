package com.fernando.mptest.service;

import com.fernando.mptest.model.Hold;
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
public interface IHoldService extends IService<Hold> {

    public List<Hold> findAllHold();

}
