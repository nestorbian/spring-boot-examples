package com.nestorbian.jackson.demo.query;

import com.nestorbian.jackson.demo.enums.CurrCode;
import com.nestorbian.jackson.demo.enums.Status;

public class Param {
    private Status status;
    private CurrCode currCode;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CurrCode getCurrCode() {
        return currCode;
    }

    public void setCurrCode(CurrCode currCode) {
        this.currCode = currCode;
    }

    @Override
    public String toString() {
        return "Param{" +
                "status=" + status +
                ", currCode=" + currCode +
                '}';
    }
}
