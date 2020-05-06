package com.nestor.hibernatevalidation.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.math.BigDecimal;

public class Car {

    @NotBlank(message = "名称不能为空")
    @Length(message = "名称长度不正确", min = 5, groups = Limit.class)
    private String name;
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

    public interface Limit extends Default {

    }
}
