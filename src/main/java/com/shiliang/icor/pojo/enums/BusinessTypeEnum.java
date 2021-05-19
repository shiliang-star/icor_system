package com.shiliang.icor.pojo.enums;

/**
 * @Author sl
 * @Date 2021/3/6 21:07
 * @Description 业务类型枚举
 */
public enum  BusinessTypeEnum {

    Manuscript("GJ", "稿件", null),
    Conference("HY", "会议", null),
    Attachment("FJ", "附件", null);


    private String code;
    private String name;
    private String description;

    BusinessTypeEnum(String code, String name, String description) {
        this.code =code;
        this.name =name;
        this.description =description;
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

    public static String getBusinessTypePrefix(String type) {
        BusinessTypeEnum[] businessTypeEnums = BusinessTypeEnum.values();
        for (BusinessTypeEnum businessTypeEnum : businessTypeEnums) {
            if (businessTypeEnum.name().equals(type)) {
                return businessTypeEnum.getCode();
            }
        }
        return null;
    }
}
