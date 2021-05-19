package com.shiliang.icor.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ExcelConference.java
 * @Description TODO
 * @createTime 2021年05月08日 23:23:00
 */
@Data
public class ExcelConference {

    public static final String bigTitle= "填写须知： \n" +
            "1.第1、2行为固定结构，不可更改；以下示例行，导入前请先删除\n" +
            "2.请严格按照填写规则输入数据，不合规的数据无法成功导入";

    @ApiModelProperty(value = "会议编码")
    @ExcelProperty(value = "会议编码",index = 0)
    private String code;

    @ApiModelProperty(value = "会议名称")
    @ExcelProperty(value = "会议名称",index = 1)
    private String name;

    @ApiModelProperty(value = "议题")
    @ExcelProperty(value = "议题",index = 2)
    private String topics;

    @ApiModelProperty(value = "主持者")
    @ExcelProperty(value = "主持者",index = 3)
    private String moderator;

    @ApiModelProperty(value = "组织者")
    @ExcelProperty(value = "组织者",index = 4)
    private String organizer;

    @ApiModelProperty(value = "与会者")
    @ExcelProperty(value = "与会者",index = 5)
    private String participants;

    @ApiModelProperty(value = "会议目的")
    @ExcelProperty(value = "会议目的",index = 6)
    private String objective;

    @ApiModelProperty(value = "会议时间")
    @ExcelProperty(value = "会议时间",index = 7)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date conferenceTime;

    @ApiModelProperty(value = "会议地址")
    @ExcelProperty(value = "会议地址",index = 8)
    private String conferenceAddress;

    @ApiModelProperty(value = "会议描述")
    @ExcelProperty(value = "会议描述",index = 9)
    private String description;

    @ApiModelProperty(value = "创建者")
    @ExcelProperty(value = "创建者",index = 10)
    private String creator;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty(value = "创建时间",index = 11)
    private Date creationTime;


    @ApiModelProperty(value = "修改者")
    @ExcelProperty(value = "修改者",index = 12)
    private String modifier;


    @ApiModelProperty(value = "修改时间")
    @ExcelProperty(value = "修改时间",index = 13)
    private Date modifiedTime;


    /**
     * 每个模板的首行高度， 换行数目+2 乘以15
     */
    public  static int getHeadHeight(){
        return  (3+2)*15;
    }

}
