package com.atguigu.gulixueyuan.ucenter.mapper;

import com.atguigu.gulixueyuan.ucenter.entity.MemberLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学员登录日志表 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLog> {

    Integer selectLoginCount(String day);
}
