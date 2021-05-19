package com.shiliang.icor.pojo.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.shiliang.icor.pojo.entity.BaseSearchFrom;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author sl
 * @Date 2021/2/24 20:55
 * @Description 稿件查询对象封装
 */
@ApiModel(value = "manuscript查询对象", description = "稿件查询对象封装")
@Data
public class ManuscriptSearchForm  extends BaseSearchFrom implements Serializable {

    @ApiModelProperty(value = "稿件编码")
    private String code;

    @ApiModelProperty(value = "稿件名称")
    private String name;

    @ApiModelProperty(value = "稿件状态")
    private String status;

    @ApiModelProperty(value = "是否审核")
    private Integer isApproved;

    @ApiModelProperty(value = "是否来自 已投列表")
    private Integer isFromVotedList;

    @ApiModelProperty(value = "是否来自 审批列表")
    private Integer isFromApprovedList;

    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "截至时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Override
    public QueryWrapper queryWrapper() {
        QueryWrapper<ManuscriptEntity> queryWrapper = new QueryWrapper<>();
        if (code != null) {
            queryWrapper.like("code", code);
        }
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        if (startTime != null && endTime!=null) {
            queryWrapper.between("creation_time", startTime, endTime);
        }
        return queryWrapper;
    }
}
