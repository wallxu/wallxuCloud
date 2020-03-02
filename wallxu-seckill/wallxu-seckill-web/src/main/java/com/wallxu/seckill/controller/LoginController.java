package com.wallxu.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.base.ResponseBean;
import com.wallxu.common.constant.ErrorCode;
import com.wallxu.common.utils.FastJsonUtil;
import com.wallxu.common.utils.RedisUtil;
import com.wallxu.seckill.dao.domain.TbMiaoshaUser;
import com.wallxu.seckill.rpc.api.MiaoshaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by Administrator on 2018/7/17.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final int USER_SESSION_EXPIRE = 60*60*24;

    @Reference
    MiaoshaUserService miaoshaUserService;


    @RequestMapping("/index")
    public String index() {
        return "/login";
    }


    @RequestMapping("/do_login")
    @ResponseBody
    public ResponseBean doLogin(@Validated TbMiaoshaUser tbMiaoshaUser, HttpServletRequest request) {
        ResponseBean resp = new ResponseBean();
        miaoshaUserService.login(tbMiaoshaUser);

        resp.setRespCode(ErrorCode.SUCCESS);
        String sessionId = UUID.randomUUID().toString();
        String user = FastJsonUtil.parseToJSON(tbMiaoshaUser);

        //登录成功，存入redis中
        request.getSession().setAttribute("token", sessionId);
        RedisUtil.set(sessionId, user, USER_SESSION_EXPIRE);
        return resp;
    }

}
