package com.shiliang.icor.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName BusinessObjectSearch.java
 * @Description TODO
 * @createTime 2021年05月19日 17:02:00
 */
@Data
public class AttachmentBusinessObjectSearch {
    @ApiModelProperty("文件属于那个表")
    private String entityType;

    @ApiModelProperty("文件属于那个表主键")
    private String entityId;
}
