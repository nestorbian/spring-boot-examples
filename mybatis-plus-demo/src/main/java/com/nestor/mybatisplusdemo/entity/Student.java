package com.nestor.mybatisplusdemo.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.nestor.mybatisplusdemo.enums.Sex;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 学生类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends BaseModel {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Sex sex;
    private BigDecimal enterScore;
    private Long schoolId;
}
