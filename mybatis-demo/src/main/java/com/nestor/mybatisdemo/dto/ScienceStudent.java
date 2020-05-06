package com.nestor.mybatisdemo.dto;

import com.nestor.mybatisdemo.po.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 理科生
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ScienceStudent extends Student {

    private BigDecimal scienceScore;

}
