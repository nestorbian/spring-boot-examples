package com.nestor.mybatisdemo.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学校
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/21
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class School implements Serializable {
    public static final long serialVersionUID = 1L;
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    private LocalDate buildDate;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
