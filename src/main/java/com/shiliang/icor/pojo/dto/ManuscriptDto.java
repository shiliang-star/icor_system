package com.shiliang.icor.pojo.dto;


import com.shiliang.icor.pojo.entity.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Manuscript数据传输对象", description="稿件数据传输对象")
public class ManuscriptDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "稿件编码")
    private String code;

    @ApiModelProperty(value = "稿件名称")
    private String name;

    @ApiModelProperty(value = "稿件分类")
    private String manuscriptClass;

    @ApiModelProperty(value = "稿件描述")
    private String description;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "投稿人")
    private Integer contributor;

    @ApiModelProperty(value = "审稿组")
    private Integer reviewerGroup;

    @ApiModelProperty(value = "所属会议")
    private String conference;

    @ApiModelProperty(value = "审稿阶段")
    private String examStage;

    @ApiModelProperty(value = "审稿意见")
    private String examOpinion;

    @ApiModelProperty(value = "审稿结果")
    private String examResult;

    @ApiModelProperty(value = "稿件状态")
    private String status;

    @ApiModelProperty(value = "主题前沿性与新颖性")
    private String titleIdea;

    @ApiModelProperty(value = "审稿时间")
    private Date approveTime;

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
