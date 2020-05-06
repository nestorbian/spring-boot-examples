package com.nestor.mybatisdemo.dto;

import com.nestor.mybatisdemo.enums.Sex;
import com.nestor.mybatisdemo.po.School;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学生DTO
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/16
 */
@Data
public class StudentDTO {

    private Long id;
    private String name;
    private Integer age;
    private Sex sex;
    private BigDecimal enterScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private School school;

}
