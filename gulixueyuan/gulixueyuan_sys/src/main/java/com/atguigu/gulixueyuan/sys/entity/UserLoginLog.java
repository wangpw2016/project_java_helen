package com.atguigu.gulixueyuan.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户用户登录日志
 * </p>
 *
 * @author Helen
 * @since 2018-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_login_log")
@ApiModel(value="UserLoginLog对象", description="系统用户用户登录日志")
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("LOG_ID")
    private Long logId;

    @ApiModelProperty(value = "登录时间")
    @TableField("LOGIN_TIME")
    private Date loginTime;

    @ApiModelProperty(value = "登录IP")
    @TableField("IP")
    private String ip;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "操作系统")
    @TableField("OS_NAME")
    private String osName;

    @ApiModelProperty(value = "浏览器")
    @TableField("USER_AGENT")
    private String userAgent;


}
