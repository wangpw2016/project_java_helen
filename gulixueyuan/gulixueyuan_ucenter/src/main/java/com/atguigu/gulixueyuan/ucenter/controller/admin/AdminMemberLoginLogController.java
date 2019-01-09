package com.atguigu.gulixueyuan.ucenter.controller.admin;

import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.ucenter.service.MemberLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author helen
 * @since 2018/12/27
 */
@Api(description="学员登录日志")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/ucenter/member-login-log")
public class AdminMemberLoginLogController {

    @Autowired
    private MemberLoginLogService memberLoginLogService;

    @ApiOperation(value = "今日登录数")
    @GetMapping(value = "login-count/{day}")
    public R loginCount(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        System.out.println("=================> ucenter 8081");
        Integer count = memberLoginLogService.loginCount(day);
        return R.ok().data(count);
    }
}
