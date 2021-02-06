package com.nestor.springabstractcacheredis.po;

import com.nestor.springabstractcacheredis.enums.GradeLevel;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table grade_param
 */
public class GradeParam extends BaseModel implements Serializable {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_param.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   年级名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_param.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   年级主任
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_param.manager_name
     *
     * @mbg.generated
     */
    private String managerName;

    /**
     * Database Column Remarks:
     *   等级
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column grade_param.level
     *
     * @mbg.generated
     */
    private GradeLevel level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table grade_param
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_param.id
     *
     * @return the value of grade_param.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_param.id
     *
     * @param id the value for grade_param.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_param.name
     *
     * @return the value of grade_param.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_param.name
     *
     * @param name the value for grade_param.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_param.manager_name
     *
     * @return the value of grade_param.manager_name
     *
     * @mbg.generated
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_param.manager_name
     *
     * @param managerName the value for grade_param.manager_name
     *
     * @mbg.generated
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column grade_param.level
     *
     * @return the value of grade_param.level
     *
     * @mbg.generated
     */
    public GradeLevel getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column grade_param.level
     *
     * @param level the value for grade_param.level
     *
     * @mbg.generated
     */
    public void setLevel(GradeLevel level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table grade_param
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}