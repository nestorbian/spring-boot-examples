package com.nestor.mybatisdemo.po;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nestor.mybatisdemo.common.BaseEnumJsonDeserializer;
import com.nestor.mybatisdemo.enums.Sex;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学生类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/21
 */
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    @JsonDeserialize(using = BaseEnumJsonDeserializer.class)
    private Sex sex;
    private BigDecimal enterScore;
    private Long schoolId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
