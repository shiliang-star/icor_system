package com.shiliang.icor.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ManuscriptVO.java
 * @Description TODO
 * @createTime 2021年04月25日 22:00:00
 */
@Data
public class ManuscriptVO {

    @ApiModelProperty(value = "稿件主键")
    private String id;

    @ApiModelProperty(value = "稿件编码")
    private String code;

    @ApiModelProperty(value = "稿件名称")
    private String name;

    @ApiModelProperty(value = "稿件分类")
    private String manuscriptClass;

    @ApiModelProperty(value = "稿件描述")
    private String description;

    @ApiModelProperty(value = "投稿人")
    private String contributor;

    @ApiModelProperty(value = "审稿人")
    private String reviewer;

    @ApiModelProperty(value = "已审人员")
    private String alreadyReviewer;

    @ApiModelProperty(value = "未审人员")
    private String unReviewer;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "所属会议")
    private String conference;

    @ApiModelProperty(value = "审稿进度")
    private Double examProgress;

    @ApiModelProperty(value = "稿件状态")
    private Integer status;

    @ApiModelProperty(value = "稿件状态名称")
    private String statusName;

    @ApiModelProperty(value = "主题前沿性与新颖性（平均）")
    private String titleIdea;

    @ApiModelProperty(value = "研究内容的创新程度（平均）")
    private String filedIdea;

    @ApiModelProperty(value = "整体学术水平（平均）")
    private String scienceLev;

    @ApiModelProperty(value = "发表价值与意义（平均）")
    private String txtValue;

    @ApiModelProperty(value = "题目准确性与吸引力（平均）")
    private String titleCharm;

    @ApiModelProperty(value = "英文摘要的写作质量（平均）")
    private String egLeval;

    @ApiModelProperty(value = "前言是否准确反映国内外最新研究现状（平均）")
    private String introReal;

    @ApiModelProperty(value = "总平均分")
    private String averageScore;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
