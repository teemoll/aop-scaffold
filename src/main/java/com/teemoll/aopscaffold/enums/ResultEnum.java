package com.teemoll.aopscaffold.enums;

/**
 * 返回响应枚举
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
public enum ResultEnum {

    SUCCESS("00000","内部错误"),
    UNKNOWN_ERROR("10000","未知错误"),
    PARAM_IS_NULL("10001","参数为空");

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

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
}
