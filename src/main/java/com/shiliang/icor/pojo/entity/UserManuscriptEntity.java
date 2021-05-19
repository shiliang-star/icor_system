package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_manuscript")
@ApiModel(value="UserManuscriptEntity对象", description="")
public class UserManuscriptEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户主键")
    private String userId;

    @ApiModelProperty(value = "稿件主键")
    private String manuscriptId;

    @ApiModelProperty(value = "是否审核")
    @TableField(fill = FieldFill.INSERT)
    private Integer isApproved;


    @ApiModelProperty(value = "分配时间")
    private Date allocateTime;

    @ApiModelProperty(value = "审稿时间")
    private Date approveTime;

    @ApiModelProperty(value = "审稿态度")
    private Integer approveAttitude;

    @ApiModelProperty(value = "审稿意见")
    private String examOpinion;

    @ApiModelProperty(value = "主题前沿性与新颖性")
    private String titleIdea;

    @ApiModelProperty(value = "研究内容的创新程度")
    private String filedIdea;

    @ApiModelProperty(value = "整体学术水平")
    private String scienceLev;

    @ApiModelProperty(value = "发表价值与意义")
    private String txtValue;

    @ApiModelProperty(value = "题目准确性与吸引力")
    private String titleCharm;

    @ApiModelProperty(value = "英文摘要的写作质量")
    private String egLeval;

    @ApiModelProperty(value = "前言是否准确反映国内外最新研究现状")
    private String introReal;

    @ApiModelProperty(value = "逻辑删除位")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
