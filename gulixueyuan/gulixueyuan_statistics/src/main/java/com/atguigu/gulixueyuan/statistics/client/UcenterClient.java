package com.atguigu.gulixueyuan.statistics.client;

import com.atguigu.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author helen
 * @since 2018/12/27
 */
@Component
//@RestController
@FeignClient("gulixueyuan-ucenter")
public interface UcenterClient {

    @GetMapping(value = "/admin/ucenter/member/register-count/{day}")
    public R registerCount(@PathVariable("day") String day);


    @GetMapping(value = "/admin/ucenter/member-login-log/login-count/{day}")
    public R loginCount(@PathVariable("day") String day);
}
