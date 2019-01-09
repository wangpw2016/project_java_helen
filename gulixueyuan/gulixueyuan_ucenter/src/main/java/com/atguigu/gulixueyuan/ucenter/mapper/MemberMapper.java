package com.atguigu.gulixueyuan.ucenter.mapper;

import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学员表 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterCount(String day);
}
