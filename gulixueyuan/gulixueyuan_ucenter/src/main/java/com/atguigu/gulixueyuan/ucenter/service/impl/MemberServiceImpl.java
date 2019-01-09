package com.atguigu.gulixueyuan.ucenter.service.impl;

import com.atguigu.gulixueyuan.ucenter.entity.Member;
import com.atguigu.gulixueyuan.ucenter.entity.handler.MyMetaObjectHandler;
import com.atguigu.gulixueyuan.ucenter.entity.query.QueryMember;
import com.atguigu.gulixueyuan.ucenter.mapper.MemberMapper;
import com.atguigu.gulixueyuan.ucenter.service.MemberService;
import com.atguigu.gulixueyuan.ucenter.utils.CommonRegex;
import com.atguigu.gulixueyuan.ucenter.utils.ExcelImportHSSFUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * <p>
 * 学员表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2018-12-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

//    @Autowired
//    private RedisTemplate redisTemplate;


    @Override
    public void pageQuery(Page<Member> pageParam, QueryMember searchObj) {

        if (searchObj == null) {
            baseMapper.selectPage(pageParam, null);
            return;
        }

        String keyWord = searchObj.getKeyWord();
        Boolean isAvailable = searchObj.getIsAvailable();
        Date begin = searchObj.getBegin();
        Date end = searchObj.getEnd();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(keyWord)) {
            queryWrapper
                    .and(i -> i.like("EMAIL", keyWord).or()
                            .like("MOBILE", keyWord).or()
                            .like("USER_NAME", keyWord).or()
                            .like("SHOW_NAME", keyWord));
        }


        if (!StringUtils.isEmpty(isAvailable)) {
            queryWrapper.eq("IS_AVAILABLE", isAvailable);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("CREATE_TIME", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("CREATE_TIME", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<String> batchImport(MultipartFile file, Integer mark) throws Exception {

        List<String> msg = new ArrayList<String>();

        ExcelImportHSSFUtil excelHSSFUtil = new ExcelImportHSSFUtil(file.getInputStream());
        HSSFSheet sheet = excelHSSFUtil.getSheet();

        int rows = sheet.getLastRowNum();//一共有多少行（zero based）
        if (rows == 0) {
            msg.add("请填写数据");
            return msg;
        }
        for (int i = 1; i <= rows; i++) {

            HSSFRow row = sheet.getRow(i);
            if (row != null) {// 行不为空

                //邮箱
                HSSFCell emailCell = row.getCell(0);
                int emailCellType = emailCell.getCellType();
                String emailValue = excelHSSFUtil.getCellValue(emailCell, emailCellType);
                if (StringUtils.isEmpty(emailValue)) {
                    msg.add("第" + i + "行邮箱为空");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }

                if (!emailValue.matches(CommonRegex.emailRegex)) {
                    msg.add("第" + i + "行邮箱格式错误");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }

                //邮箱是否已经注册
                if (this.checkEmailExist(emailValue)) {
                    msg.add("第" + i + "行邮箱已存在");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }

                //手机
                HSSFCell mobileCell = row.getCell(1);
                int mobileCellType = mobileCell.getCellType();
                String mibileValue = excelHSSFUtil.getCellValue(mobileCell, mobileCellType);
                if (StringUtils.isEmpty(mibileValue)) {
                    msg.add("第" + i + "行手机为空");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }

                if (!mibileValue.matches(CommonRegex.telRegex)) {
                    msg.add("第" + i + "行手机格式错误");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }


                //手机是否已经注册
                if (this.checkEmailExist(mibileValue)) {
                    msg.add("第" + i + "行手机已存在");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }


                //密码
                HSSFCell passwordCell = row.getCell(2);
                int passwordCellType = passwordCell.getCellType();
                String passwordValue = excelHSSFUtil.getCellValue(passwordCell, passwordCellType);
                if (StringUtils.isEmpty(passwordValue)) {
                    msg.add("第" + i + "行密码为空");
                    if (mark == 1) {
                        continue;
                    } else {
                        return msg;
                    }
                }

                Member member = new Member();
                member.setEmail(emailValue);//用户学员邮箱
                member.setMobile(mibileValue);//用户学员手机
                member.setPassword(passwordValue);//用户学员密码
                member.setIsAvailable(true);
                member.setMsgNum(0);
                member.setSysMsgNum(0);

                //生成六位数字随机数：使用lang3工具类
                String name = RandomStringUtils.randomAlphabetic(10);
                member.setUserName("用户" + name);
                member.setPicImg("https://gulixueyuan-helen.oss-cn-beijing.aliyuncs.com/pic/avatar.png");
                member.setLastSystemTime(new Date());//上传统计系统消息时间
                baseMapper.insert(member);//添加学员
            }
        }
        return msg;
    }


    /**
     * 验证邮箱是否已存在
     * @param email
     * @return
     */
    public boolean checkEmailExist(String email) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("EMAIL", email);
        int count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    /**
     * 验证手机号是否已存在
     * @param mobile
     * @return
     */
    public boolean checkMobileExist(String mobile) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MOBILE", mobile);
        int count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }


    @Override
    public Integer registerCount(String day) {
        /*QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DATE_FORMAT(CREATE_TIME,'%Y-%m-%d')", day);
        queryWrapper.in("DELETED", 0, 1);*/
        return baseMapper.selectRegisterCount(day);
    }


    @Override
    public void sendSms(String mobile) {

        //生成六位数字随机数：使用lang3工具类
        String verificationCode = RandomStringUtils.randomNumeric(6);

        //记住验证码，以便登录时验证：存入缓存
        //redisTemplate.opsForValue().set("checkcode_"+mobile, checkcode, 6, TimeUnit.HOURS);

        //发送验证码：使用阿里云或其他第三方短信服务
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("verificationCode", verificationCode);
        //rabbitTemplate.convertAndSend("sms", map);
        //测试用
        LOGGER.info("验证码为：" + verificationCode);
    }
}
