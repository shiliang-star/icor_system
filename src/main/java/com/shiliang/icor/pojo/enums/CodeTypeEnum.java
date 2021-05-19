package com.shiliang.icor.pojo.enums;

/**
 * @description:
 * @Author: sl
 */
public enum CodeTypeEnum {
    Manuscript("01","稿件",null),
    CONFERENCE("02","会议",null);

    private String code;
    private String name;
    private String description;

    CodeTypeEnum(String code, String name, String description) {
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
