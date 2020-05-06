package com.nestor.mybatisdemo.enums;

public enum GradeLevel implements BaseEnum<Integer> {
    FIRST(1, "一年级"), SECOND(2, "二年级"), THIRD(3, "三年级"), FOURTH(4, "四年级");
    private Integer code;
    private String desc;

    GradeLevel(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
