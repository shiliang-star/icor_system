package com.shiliang.icor.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName UserVO.java
 * @Description TODO
 * @createTime 2021年04月25日 13:24:00
 */
@Data
public class UserVO {

    @ApiModelProperty(value = "用户主键")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;
}
