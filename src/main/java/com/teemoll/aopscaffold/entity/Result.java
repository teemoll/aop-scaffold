package com.teemoll.aopscaffold.entity;

import com.teemoll.aopscaffold.enums.ResultEnum;

/**
 * 通用返回类
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
public class Result <T> {

    private String code;
    private String msg;
    private T data;

    public Result() {
        super();
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功且带数据
     * @param object 返回数据
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功但不带数据
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 失败
     * @param code 错误码
     * @param msg 错误提示
     * @return
     */
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
