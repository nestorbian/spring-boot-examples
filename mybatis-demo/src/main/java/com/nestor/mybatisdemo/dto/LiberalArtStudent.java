package com.nestor.mybatisdemo.dto;

import com.nestor.mybatisdemo.po.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 文科生
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LiberalArtStudent extends Student {

    private BigDecimal liberalArtScore;

}
