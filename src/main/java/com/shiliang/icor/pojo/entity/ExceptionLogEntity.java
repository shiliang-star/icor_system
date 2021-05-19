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
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("t_exception_log")
@ApiModel(value="ExceptionLogEntity对象", description="")
public class ExceptionLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "exc_id", type = IdType.ID_WORKER_STR)
    private String excId;

    @ApiModelProperty(value = "请求参数")
    private String excRequParam;

    @ApiModelProperty(value = "异常名称")
    private String excName;

    @ApiModelProperty(value = "异常信息")
    private String excMessage;

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

    @ApiModelProperty(value = "操作版本号")
    private String operVersion;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operCreateTime;


}
