package com.shiliang.icor.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName UserApproveVO.java
 * @Description TODO
 * @createTime 2021年04月25日 20:54:00
 */
@Data
public class UserApproveVO {

    @ApiModelProperty(value = "稿件主键")
    private String manuscriptId;


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
}
