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
 * 权限表
 * </p>
 *
 * @author Helen
 * @since 2018-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_function")
@ApiModel(value="Function对象", description="权限表")
public class Function implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID")
    @TableId("FUNCTION_ID")
    private Long functionId;

    @ApiModelProperty(value = "权限父ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "权限名")
    @TableField("FUNCTION_NAME")
    private String functionName;

    @ApiModelProperty(value = "权限URL")
    @TableField("FUNCTION_URL")
    private String functionUrl;

    @ApiModelProperty(value = "权限类型 1菜单 2功能")
    @TableField("FUNCTION_TYPE")
    private Boolean functionType;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "图标路径")
    @TableField("IMAGE_URL")
    private String imageUrl;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
