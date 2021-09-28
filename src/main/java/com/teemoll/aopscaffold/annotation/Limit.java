package com.teemoll.aopscaffold.annotation;

import com.teemoll.aopscaffold.enums.LimitType;

import java.lang.annotation.*;

/**
 * 自定义限流注解
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {

    /**
     * 名字
     */
    String name() default "";

    /**
     * key
     */
    String key() default "";

    /**
     * Key的前缀
     */
    String prefix() default "";

    /**
     * 给定的时间范围 单位(秒)，表示请求限制时间段
     */
    int period();

    /**
     * 在period这个时间段内允许放行请求的次数
     */
    int count();

    /**
     * 限流的类型(用户自定义key 或者 请求ip)，默认用方法名作为默认key
     */
    LimitType limitType() default LimitType.CUSTOMER;

}
