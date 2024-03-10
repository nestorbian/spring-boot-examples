package com.nestor.hibernatevalidation.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class Car implements Serializable {

    private static final long serialVersionUID = 3246433079484650167L;
    @NotBlank(message = "{NotBlank.errorMsg}")
    @Length(message = "名称长度不正确", min = 5, groups = Limit.class)
    private String name;
    @NotNull
    private BigDecimal amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    // @GroupSequence(Default.class)
    public interface Limit {

    }
}
