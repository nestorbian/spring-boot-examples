package com.nestor.springabstractcacheredis.po;

import com.nestor.springabstractcacheredis.enums.GradeLevel;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * grade_param
 */
public class GradeParam extends BaseModel implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 年级名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 年级主任
     *
     * @mbg.generated
     */
    private String managerName;

    /**
     * 等级
     *
     * @mbg.generated
     */
    private GradeLevel level;

    /**
     * grade_param
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 年级名称
     * @return name 年级名称
     */
    public String getName() {
        return name;
    }

    /**
     * 年级名称
     * @param name 年级名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 年级主任
     * @return manager_name 年级主任
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 年级主任
     * @param managerName 年级主任
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * 等级
     * @return level 等级
     */
    public GradeLevel getLevel() {
        return level;
    }

    /**
     * 等级
     * @param level 等级
     */
    public void setLevel(GradeLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}