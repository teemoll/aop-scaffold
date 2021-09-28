package com.teemoll.aopscaffold.controller;

import com.teemoll.aopscaffold.annotation.Limit;
import com.teemoll.aopscaffold.constant.RedisKeyConstant;
import com.teemoll.aopscaffold.enums.LimitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试限流
 *
 * @author: teemoll_
 * @Date: 2021/9/28
 */
@RestController
public class LimiterTestController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    @Autowired
    private HttpServletResponse response;



    /**
     * 限流:1秒内最多请求32次
     */
    @Limit(period = 1, count = 2)
    @GetMapping("/limitTest1")
    public int testLimiter1() {
        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    /**
     * 限流:1秒内最多请求2次，自定义key
     */
    @Limit(key = RedisKeyConstant.CUSTOMER_LIMIT_TEST, period = 1, count = 2, limitType = LimitType.CUSTOMER)
    @GetMapping("/limitTest2")
    public int testLimiter2() {
        SimpleDateFormat format = new SimpleDateFormat("EEE,MMM yyy HH: mm: ss 'GMT'", Locale.US);
        //当前时间
        long now = System.currentTimeMillis() * 1000 * 1000;
        response.addHeader("Date", format.format(new Date()));
        //绝对过期时间(单位：分钟)，http 1.0 支持
        response.addHeader("Expires", format.format(new Date(now + 20 * 1000)));
        //相对过期时间(单位：秒) http 1.1 支持，max-age=20代表：20秒后再来请求服务器，期间读缓存
        response.addHeader("Cache-control", "max-age=20");
        //注意：Cache-Control的优先级高于Expires
        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    /**
     * 限流:1秒内同一IP最多请求2次
     */
    @Limit(key = RedisKeyConstant.IP_LIMIT_TEST, period = 1, count = 2, limitType = LimitType.IP)
    @GetMapping("/limitTest3")
    public int testLimiter3() {
        return ATOMIC_INTEGER_3.incrementAndGet();
    }

}
