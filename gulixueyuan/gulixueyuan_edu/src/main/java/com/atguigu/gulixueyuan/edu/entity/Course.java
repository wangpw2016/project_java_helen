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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Helen
 * @since 2018-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_course")
@ApiModel(value="Course对象", description="课程")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程编号")
    @TableId("COURSE_ID")
    private String courseId;

    @ApiModelProperty(value = "课程名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "课程专业ID")
    @TableField("SUBJECT_ID")
    private String subjectId;

    @ApiModelProperty(value = "课程专业链")
    @TableField("SUBJECT_LINK")
    private String subjectLink;

    @ApiModelProperty(value = "课程原价格（只显示）")
    @TableField("SOURCE_PRICE")
    private BigDecimal sourcePrice;

    @ApiModelProperty(value = "课程销售价格（实际支付价格）设置为0则可免费观看")
    @TableField("CURRENT_PRICE")
    private BigDecimal currentPrice;

    @ApiModelProperty(value = "课程简介")
    @TableField("INTRO")
    private String intro;

    @ApiModelProperty(value = "总课时")
    @TableField("LESSION_NUM")
    private Integer lessionNum;

    @ApiModelProperty(value = "课程图片路径")
    @TableField("LOGO")
    private String logo;

    @ApiModelProperty(value = "销售数量")
    @TableField("PAGE_BUYCOUNT")
    private Long pageBuycount;

    @ApiModelProperty(value = "浏览数量")
    @TableField("PAGE_VIEWCOUNT")
    private Long pageViewcount;

    @ApiModelProperty(value = "有效结束时间")
    @TableField("END_TIME")
    private Date endTime;

    @ApiModelProperty(value = "有效期类型，0：到期时间，1：按天数")
    @TableField("LOSE_TYPE")
    private Integer loseType;

    @ApiModelProperty(value = "有效期:商品订单过期时间点")
    @TableField("LOSE_TIME")
    private String loseTime;

    @ApiModelProperty(value = "该课程总共卖了多少钱（新加字段暂时没用到）")
    @TableField("COURSE_TOTAL_INCOME")
    private BigDecimal courseTotalIncome;

    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除 1已删除 0未删除")
    @TableField("DELETED")
    private Boolean deleted;


}
