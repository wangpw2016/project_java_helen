package com.atguigu.gulixueyuan.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Helen
 * @since 2018-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="系统用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "登录名")
    @TableField("LOGIN_NAME")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    @TableField("LOGIN_PWD")
    private String loginPwd;

    @ApiModelProperty(value = "用户真实姓名名")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "所属角色ID")
    @TableField("ROLE_ID")
    private Long roleId;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @ApiModelProperty(value = "最后登录IP")
    @TableField("LAST_LOGIN_IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "邮件地址")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "手机号码")
    @TableField("TEL")
    private String tel;

    @ApiModelProperty(value = "是否可用 1正常  0冻结")
    @TableField("STATUS")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除 1已删除 0未删除")
    @TableLogic
    @TableField(value = "DELETED", fill = FieldFill.INSERT)
    private Boolean deleted;
}
