package com.atguigu.gulixueyuan.ucenter.controller.admin;


import com.atguigu.entity.PageResult;
import com.atguigu.entity.R;
import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.entity.query.QueryMember;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
@RequestMapping("/admin/ucenter/member")
public class AdminMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "所有学员列表")
    @GetMapping
    public R list(){
        List<Member> list = memberService.list(null);
        return R.ok().data(list);
    }


    @ApiOperation(value = "分页学员列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "searchMap", value = "查询对象", required = false)
            @RequestBody(required = false) QueryMember searchObj){

        Page<Member> pageParam = new Page<Member>(page, limit);

        memberService.pageQuery(pageParam, searchObj);
        List<Member> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        PageResult<Member> result = new PageResult<Member>(total, records);

        return  R.ok().data(result);
    }

    @ApiOperation(value = "根据ID删除学员")
    @DeleteMapping(value = "{memberId}")
    public R deleteById(
            @ApiParam(name = "memberId", value = "学员ID", required = true)
            @PathVariable Long memberId){
        memberService.removeById(memberId);
        return R.ok();
    }


    @PostMapping("import")
    public R addUser(@RequestParam("file") MultipartFile file,
                     @RequestParam("mark") Integer mark) throws Exception {

        List<String> msg = memberService.batchImport(file, mark);
        if(msg.size() == 0){
            return R.ok().message("批量开通成功");
        }else{
            return R.error().message("批量开通失败").data(msg);
        }

    }


    @ApiOperation(value = "今日注册数")
    @GetMapping(value = "register-count/{day}")
    public R registerCount(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        System.out.println("=================> ucenter 8081");
        Integer count = memberService.registerCount(day);
        return R.ok().data(count);
    }

}

