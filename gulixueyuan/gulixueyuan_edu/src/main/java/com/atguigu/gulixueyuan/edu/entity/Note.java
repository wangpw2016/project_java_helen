package com.atguigu.gulixueyuan.edu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程笔记
 * </p>
 *
 * @author Helen
 * @since 2018-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_note")
@ApiModel(value="Note对象", description="课程笔记")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("NOTE_ID")
    private String noteId;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "课程id")
    @TableField("COURSE_ID")
    private String courseId;

    @ApiModelProperty(value = "节点ID")
    @TableField("KPOINT_ID")
    private String kpointId;

    @ApiModelProperty(value = "笔记信息")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "0公开1隐藏")
    @TableField("STATUS")
    private Boolean status;


}
