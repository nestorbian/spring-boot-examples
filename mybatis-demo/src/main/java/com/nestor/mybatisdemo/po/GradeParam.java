package com.nestor.mybatisdemo.po;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nestor.mybatisdemo.common.BaseEnumJsonDeserializer;
import com.nestor.mybatisdemo.enums.GradeLevel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 年级参数类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/20
 */
@Data
public class GradeParam extends BaseModel implements Serializable {
    public static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String managerName;
    @JsonDeserialize(using = BaseEnumJsonDeserializer.class)
    private GradeLevel level;
}
