package com.nestor.hibernatevalidation.entity;

import com.nestor.hibernatevalidation.annotation.Desensitization;
import com.nestor.hibernatevalidation.enums.DesensitizationTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/21
 */

@Data
public class TestAnnotationDTO implements Serializable {
    /**
     * 自定义
     */
    @Desensitization(type = DesensitizationTypeEnum.CUSTOMER,startInclude = 5,endExclude = 10)
    private String custom;
    /**
     * 手机号
     */
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;
    /**
     * 邮箱
     */
    @Desensitization(type = DesensitizationTypeEnum.EMAIL)
    private String email;
    /**
     * 身份证
     */
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;
}
