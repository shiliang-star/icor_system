package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;


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
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_manuscript")
@ApiModel(value="Manuscript对象", description="")
public class ManuscriptEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value = "浏览数量")
    @TableField(fill = FieldFill.INSERT)
    private Long viewCount;

    @ApiModelProperty(value = "所属会议")
    private String conferenceId;

    @ApiModelProperty(value = "审稿进度")
    @TableField(fill = FieldFill.INSERT)
    private Double examProgress;

    @ApiModelProperty(value = "稿件状态")
    private Integer status;

    @Version
    @ApiModelProperty(value = "乐观锁")
    @TableField(fill = FieldFill.INSERT)
    private Long version;

    @ApiModelProperty(value = "备注")
    private String remark;

}
