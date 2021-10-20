package com.fernando.mptest.service.impl;

import com.fernando.mptest.model.Expert;
import com.fernando.mptest.mapper.ExpertMapper;
import com.fernando.mptest.service.IExpertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Service
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements IExpertService {

}
