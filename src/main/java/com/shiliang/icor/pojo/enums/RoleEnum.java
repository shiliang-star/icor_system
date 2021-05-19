package com.shiliang.icor.pojo.enums;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName RoleEnum.java
 * @Description TODO
 * @createTime 2021年04月27日 17:03:00
 */
public enum RoleEnum {

    ADMIN("ADMIN","管理员","拥有所有权限"),
    REVIEW("REVIEW","审稿专员","负责审稿"),
    CONTRIBUTOR("CONTRIBUTOR","普通用户","负责投稿");

    private String code;
    private String name;
    private String description;

    RoleEnum(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }


    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }
}
