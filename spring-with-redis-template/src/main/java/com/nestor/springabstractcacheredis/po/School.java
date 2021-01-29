package com.nestor.springabstractcacheredis.po;

import java.io.Serializable;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

/**
 * school
 */
public class School extends BaseModel implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 地址
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 建校时间
     *
     * @mbg.generated
     */
    private LocalDate buildDate;

    /**
     * 描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * school
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
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 建校时间
     * @return build_date 建校时间
     */
    public LocalDate getBuildDate() {
        return buildDate;
    }

    /**
     * 建校时间
     * @param buildDate 建校时间
     */
    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}