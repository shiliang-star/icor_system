package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

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
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_attachment")
@ApiModel(value="AttachmentEntity对象", description="")
public class AttachmentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件编码")
    private String code;

    @ApiModelProperty(value = "附件名称")
    private String name;

    @ApiModelProperty(value = "附件存储在OSS上的URL")
    private String url;

    @ApiModelProperty(value = "文件属于那个表")
    private String entityType;

    @ApiModelProperty(value = "文件属于那个表主键")
    private String entityId;

    @ApiModelProperty(value = "上传人")
    @TableField(fill = FieldFill.INSERT)
    private String uploadPerson;


    @ApiModelProperty(value = "备注")
    private String remark;


}
