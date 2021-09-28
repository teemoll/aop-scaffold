package com.teemoll.aopscaffold.config;

import com.teemoll.aopscaffold.entity.Result;
import com.teemoll.aopscaffold.enums.ResultEnum;
import com.teemoll.aopscaffold.exception.ServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常统一处理
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.error(ResultEnum.PARAM_IS_NULL.getCode(), message);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.error(ResultEnum.PARAM_IS_NULL.getCode(), message);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.error(ResultEnum.PARAM_IS_NULL.getCode(), message);
    }


    //自定义异常
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result ServiceExceptionHandler(ServiceException e) {
        return Result.error(ResultEnum.PARAM_IS_NULL.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
    }

}
