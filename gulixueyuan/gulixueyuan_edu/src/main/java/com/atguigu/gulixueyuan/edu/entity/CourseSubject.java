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

/**
 * <p>
 * 课程和课程分类关联表
 * </p>
 *
 * @author Helen
 * @since 2018-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_course_subject")
@ApiModel(value="CourseSubject对象", description="课程和课程分类关联表")
public class CourseSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "课程id")
    @TableField("COURSE_ID")
    private String courseId;

    @ApiModelProperty(value = "分类id")
    @TableField("SUBJECT_ID")
    private String subjectId;


}
