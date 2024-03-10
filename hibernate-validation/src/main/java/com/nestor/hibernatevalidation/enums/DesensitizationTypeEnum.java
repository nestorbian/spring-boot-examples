package com.nestor.hibernatevalidation.enums;

/**
 * 脱敏数据类型
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/21
 */
public enum DesensitizationTypeEnum {
    //自定义
    CUSTOMER,
    //用户id
    USER_ID,
    //中文名
    CHINESE_NAME,
    //身份证号
    ID_CARD,
    //座机号
    FIXED_PHONE,
    //手机号
    MOBILE_PHONE,
    //地址
    ADDRESS,
    //电子邮件
    EMAIL,
    //密码
    PASSWORD,
    //中国大陆车牌，包含普通车辆、新能源车辆
    CAR_LICENSE,
    //银行卡
    BANK_CARD
}