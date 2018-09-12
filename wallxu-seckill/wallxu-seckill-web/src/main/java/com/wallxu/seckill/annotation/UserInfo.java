package com.wallxu.seckill.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/7/31.
 */

//获取用户请求参数
//使用这个注解的地方，就会验证用户token

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserInfo {
    // 当前用户在request中的token值
    String value() default "token";
}
