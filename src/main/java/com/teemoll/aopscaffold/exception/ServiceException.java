package com.teemoll.aopscaffold.exception;

import com.teemoll.aopscaffold.enums.ResultEnum;

/**
 * 自定义异常类
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(ResultEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
