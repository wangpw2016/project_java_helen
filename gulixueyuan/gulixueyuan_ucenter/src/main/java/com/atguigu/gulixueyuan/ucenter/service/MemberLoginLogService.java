package com.atguigu.gulixueyuan.ucenter.service;

import com.atguigu.gulixueyuan.ucenter.entity.MemberLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学员登录日志表 服务类
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
public interface MemberLoginLogService extends IService<MemberLoginLog> {

    Integer loginCount(String day);
}
