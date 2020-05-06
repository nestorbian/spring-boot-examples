package com.nestor.mybatisdemo.dto;

import com.nestor.mybatisdemo.po.Student;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 学校DTO
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/17
 */
@Data
public class SchoolDTO {

    private Long id;
    private String name;
    private String address;
    private LocalDate buildDate;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Student> students;
}
