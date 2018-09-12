package com.wallxu.seckill.interceptor;

import com.wallxu.common.utils.FastJsonUtil;
import com.wallxu.common.utils.RedisUtil;
import com.wallxu.seckill.annotation.UserInfo;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Administrator on 2018/7/31.
 */

/**
　　* @Description: 用户token处理，判断用户登录状态
　　* @author Administrator
　　* @date 2018/8/1 9:46
　　*/
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.hasParameterAnnotation(UserInfo.class)){
            return true; //需要继续验证
        }
        //不包含这个参数，不需要验证
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserInfo userInfo = methodParameter.getParameterAnnotation(UserInfo.class);
//      HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        //从session的scope里取UserInfo注解里的value属性值的key的value
        String token = (String)nativeWebRequest.getAttribute(userInfo.value(), NativeWebRequest.SCOPE_SESSION);
        //判断redis里数据是否过期
        String tokenVal = RedisUtil.get(token);
        //成功后，只能返回对象，返回string不行。失败返回null即可
        return tokenVal == null? null: FastJsonUtil.parseToClass(tokenVal, TbMiaoshaUser.class);
    }
}
