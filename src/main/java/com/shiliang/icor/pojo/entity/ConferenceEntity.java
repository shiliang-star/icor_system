package com.shiliang.icor.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ShiLiang
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_conference")
@ApiModel(value="ConferenceEntity对象", description="会议实体类")
public class ConferenceEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会议编码")
    private String code;

    @ApiModelProperty(value = "会议名称")
    private String name;

    @ApiModelProperty(value = "议题")
    private String topics;

    @ApiModelProperty(value = "主持者")
    private String moderator;

    @ApiModelProperty(value = "组织者")
    private String organizer;

    @ApiModelProperty(value = "与会者")
    private String participants;

    @ApiModelProperty(value = "会议目的")
    private String objective;

    @ApiModelProperty(value = "会议时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date conferenceTime;

    @ApiModelProperty(value = "会议地址")
    private String conferenceAddress;

    @ApiModelProperty(value = "会议描述")
    private String description;

    @Version
    @ApiModelProperty(value = "乐观锁")
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}
