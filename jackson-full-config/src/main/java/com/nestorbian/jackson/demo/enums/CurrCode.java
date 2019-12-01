package com.nestorbian.jackson.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum CurrCode implements BaseEnum {
    RMB("156", "rmb"), USD("840", "usd");

    private String code;
    private String desc;

    CurrCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public static CurrCode of(@JsonProperty("code") String c) {
        for (CurrCode value : CurrCode.values()) {
            if (value.getCode().equals(c)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "CurrCode{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public String getValue() {
        return getCode();
    }
}
