package com.shiliang.icor.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据传输对象的抽象基类。
 *
 * @author sl
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "数据传输对象的抽象基类", description = "数据传输对象的抽象基类")
public abstract class BaseDto implements Serializable {

    @ApiModelProperty(value = "主键", position = 1)
    @Size(max = 40)
    protected String id;

    @ApiModelProperty(value = "删除标识", position = 2, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    protected Integer isDeleted = 0;


    @ApiModelProperty(value = "创建人", position = 4)
    @Size(max = 20)
    protected String creator;

    @ApiModelProperty(value = "创建时间", position = 5)
    @Size(max = 19)
    protected Date creationTime;

    @ApiModelProperty(value = "修改人", position = 6)
    @Size(max = 20)
    protected String modifier;

    @ApiModelProperty(value = "修改时间", position = 7)
    @Size(max = 19)
    protected Date modifiedTime;

}
