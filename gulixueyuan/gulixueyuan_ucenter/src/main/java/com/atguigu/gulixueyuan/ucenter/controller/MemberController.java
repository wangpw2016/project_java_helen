package com.atguigu.gulixueyuan.ucenter.controller;


import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学员表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
@Api(description="学员管理")//对api资源的描述（热部署不好使）
@CrossOrigin //跨域
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(
            value = "注册学员",
            notes = "memberId，createTime，deleted自动生成，无需添加"
    )
    @PostMapping
    public R register(
            @ApiParam(name = "member", value = "学员对象json", required = true)
            @RequestBody Member member){
        memberService.save(member);
        return R.ok();
    }

    /**
     * 发送短信验证码
     */
    @PostMapping(value = "/send-sms/{mobile}")
    public R sendSms(@PathVariable String mobile){
        memberService.sendSms(mobile);
        return R.ok().message("发送成功");
    }
}

