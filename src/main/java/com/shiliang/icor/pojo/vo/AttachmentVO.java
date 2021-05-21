package com.shiliang.icor.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName AttachementVO.java
 * @Description TODO
 * @createTime 2021年05月19日 17:04:00
 */
@Data
public class AttachmentVO {

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
}
