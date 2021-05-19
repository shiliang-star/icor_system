package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_operation_log")
@ApiModel(value="OperationLogEntity对象", description="")
public class OperationLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "oper_id", type = IdType.ID_WORKER_STR)
    private String operId;

    @ApiModelProperty(value = "功能模块")
    private String operModul;

    @ApiModelProperty(value = "操作类型")
    private String operType;

    @ApiModelProperty(value = "操作描述")
    private String operDesc;

    @ApiModelProperty(value = "请求参数")
    private String operRequParam;

    @ApiModelProperty(value = "返回参数")
    private String operRespParam;

    @ApiModelProperty(value = "操作员ID")
    private String operUserId;

    @ApiModelProperty(value = "操作员名称")
    private String operUserName;

    @ApiModelProperty(value = "操作方法")
    private String operMethod;

    @ApiModelProperty(value = "请求URI")
    private String operUri;

    @ApiModelProperty(value = "请求IP")
    private String operIp;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operCreateTime;

    @ApiModelProperty(value = "操作版本号")
    private String operVersion;


}
