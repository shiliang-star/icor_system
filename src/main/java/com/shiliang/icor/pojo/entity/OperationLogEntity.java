package com.shiliang.icor.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty(value = "功能模块",index = 0)
    private String operModul;

    @ApiModelProperty(value = "操作类型")
    @ExcelProperty(value = "操作类型",index = 1)
    private String operType;

    @ApiModelProperty(value = "操作描述")
    @ExcelProperty(value = "操作描述",index = 2)
    private String operDesc;

    @ApiModelProperty(value = "请求参数")
    @ExcelProperty(value = "请求参数",index = 3)
    private String operRequParam;

    @ApiModelProperty(value = "返回参数")
    @ExcelProperty(value = "返回参数",index = 4)
    private String operRespParam;

    @ApiModelProperty(value = "操作员ID")
    private String operUserId;

    @ApiModelProperty(value = "操作员名称")
    @ExcelProperty(value = "操作员名称",index = 5)
    private String operUserName;

    @ApiModelProperty(value = "操作方法")
    @ExcelProperty(value = "操作方法",index = 6)
    private String operMethod;

    @ApiModelProperty(value = "请求URI")
    @ExcelProperty(value = "请求URI",index = 7)
    private String operUri;

    @ApiModelProperty(value = "请求IP")
    @ExcelProperty(value = "请求IP",index = 8)
    private String operIp;

    @ApiModelProperty(value = "操作时间")
    @ExcelProperty(value = "操作时间",index = 9)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operCreateTime;

    @ApiModelProperty(value = "操作版本号")
    @ExcelProperty(value = "操作版本号",index = 10)
    private String operVersion;


}
