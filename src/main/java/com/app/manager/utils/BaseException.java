package com.app.manager.utils;

/**
 * @author chenxf
 * Created on 2019/9/9
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -8602849407633411227L;
    /**
     * 异常代码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    public BaseException(int code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(code + ":" + msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
