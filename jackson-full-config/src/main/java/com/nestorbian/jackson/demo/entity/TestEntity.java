package com.nestorbian.jackson.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * xxx
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/11/21
 */
@Data
public class TestEntity {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate localDate;
    // private Date localDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime localDateTime;
    // private Date localDateTime;

}
