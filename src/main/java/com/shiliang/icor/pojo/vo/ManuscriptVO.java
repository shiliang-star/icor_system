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

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "所属会议")
    private String conference;

    @ApiModelProperty(value = "审稿阶段")
    private String examStage;

    @ApiModelProperty(value = "稿件状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
