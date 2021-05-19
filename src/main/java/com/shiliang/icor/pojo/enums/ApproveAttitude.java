package com.shiliang.icor.pojo.enums;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ApproveAttitude.java
 * @Description 审稿人态度
 * @createTime 2021年04月25日 20:57:00
 */
public enum ApproveAttitude {
    AGREE(1,"同意",null),
    DISAGREE(0,"不同意",null),
    REJECT(-1,"驳回到投稿人",null);

    private Integer code;
    private String name;
    private String description;

    ApproveAttitude(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }


    public Integer getCode() {
        return code;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
}
