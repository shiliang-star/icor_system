package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @since 2021-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_code_generator")
@ApiModel(value="CodeGeneratorEntity对象", description="")
public class CodeGeneratorEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流水编码号")
    @TableField("serialCode")
    private String serialCode;

    @ApiModelProperty(value = "所属业务类型")
    private String type;

    @ApiModelProperty(value = "编码规则")
    private String rule;

    @Version
    @ApiModelProperty(value = "乐观锁")
    @TableField(fill = FieldFill.INSERT)
    private Long version;

    @ApiModelProperty(value = "备注")
    private String remark;

}
