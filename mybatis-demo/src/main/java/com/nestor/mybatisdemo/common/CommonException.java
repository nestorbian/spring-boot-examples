package com.nestor.mybatisdemo.common;

/**
 * 自定义异常
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/5/6
 */
public class CommonException extends RuntimeException {

    private String code;
    private String msg;

    public CommonException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
