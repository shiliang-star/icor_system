package com.shiliang.icor.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ExcelManuscript.java
 * @Description TODO
 * @createTime 2021年05月15日 19:35:00
 */
@Data
public class ExcelManuscript {
    @ApiModelProperty(value = "稿件编码")
    @ExcelProperty(value = "稿件编码",index = 0)
    private String code;

    @ApiModelProperty(value = "稿件名称")
    @ExcelProperty(value = "稿件名称",index = 1)
    private String name;

    @ApiModelProperty(value = "稿件分类")
    @ExcelProperty(value = "稿件分类",index = 2)
    private String manuscriptClass;

    @ApiModelProperty(value = "稿件描述")
    @ExcelProperty(value = "稿件描述",index = 3)
    private String description;

    @ApiModelProperty(value = "投稿人")
    @ExcelProperty(value = "投稿人",index = 4)
    private String contributor;

    @ApiModelProperty(value = "浏览数量")
    @ExcelProperty(value = "浏览数量",index = 5)
    private Long viewCount;

    @ApiModelProperty(value = "所属会议")
    @ExcelProperty(value = "所属会议",index = 6)
    private String conferenceId;

    @ApiModelProperty(value = "审稿阶段")
    @ExcelProperty(value = "审稿阶段",index = 7)
    private String examStage;

    @ApiModelProperty(value = "稿件状态")
    @ExcelProperty(value = "稿件状态",index = 8)
    private Integer status;

    @ApiModelProperty(value = "创建者")
    @ExcelProperty(value = "创建者",index = 9)
    private String creator;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty(value = "创建时间",index = 10)
    private Date creationTime;


    @ApiModelProperty(value = "修改者")
    @ExcelProperty(value = "修改者",index = 11)
    private String modifier;


    @ApiModelProperty(value = "修改时间")
    @ExcelProperty(value = "修改时间",index = 12)
    private Date modifiedTime;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(value = "修改时间",index = 13)
    private String remark;

    /**
     * 每个模板的首行高度， 换行数目+2 乘以15
     */
    public  static int getHeadHeight(){
        return  (3+2)*15;
    }

}
