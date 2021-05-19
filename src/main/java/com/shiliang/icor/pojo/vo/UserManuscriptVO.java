package com.shiliang.icor.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName UserManuscriptVO.java
 * @Description TODO
 * @createTime 2021年04月26日 22:29:00
 */
@Data
public class UserManuscriptVO {

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "稿件编码")
    private String manuscriptCode;

    @ApiModelProperty(value = "稿件名称")
    private String manuscriptName;

    @ApiModelProperty(value = "是否审核")
    private Integer isApproved;

    @ApiModelProperty(value = "分配时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date allocateTime;

    @ApiModelProperty(value = "审稿时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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

    @ApiModelProperty(value = "创新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date gmtModified;




}
