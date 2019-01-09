package com.atguigu.gulixueyuan.sys.service.impl;

import com.atguigu.gulixueyuan.sys.entity.User;
import com.atguigu.gulixueyuan.sys.mapper.UserMapper;
import com.atguigu.gulixueyuan.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2018-12-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
