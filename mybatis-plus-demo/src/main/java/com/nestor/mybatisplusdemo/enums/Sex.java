package com.nestor.mybatisplusdemo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum Sex implements IEnum<String> {
    MALE("M", "男"), FEMALE("F", "女");

    private String code;
    private String desc;

    Sex(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return code;
    }
}
