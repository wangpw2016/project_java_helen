package com.atguigu.gulixueyuan.ucenter.service.impl;

import com.atguigu.gulixueyuan.ucenter.entity.MemberLoginLog;
import com.atguigu.gulixueyuan.ucenter.mapper.MemberLoginLogMapper;
import com.atguigu.gulixueyuan.ucenter.service.MemberLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学员登录日志表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
@Service
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog> implements MemberLoginLogService {

    @Override
    public Integer loginCount(String day) {
        return baseMapper.selectLoginCount(day);
    }
}
