package com.shiliang.icor.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName AttachmentSearchForm.java
 * @Description 附件查询对象
 * @createTime 2021年05月17日 17:32:00
 */
@Data
public class AttachmentSearchForm {

    @ApiModelProperty(value = " 会议编码")
    private String code;

    @ApiModelProperty(value = "会议名称")
    private String name;


    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "截至时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
