package com.wallxu.seckill.exception;

import com.wallxu.common.base.ResponseBean;
import com.wallxu.common.constant.ErrorCode;
import com.wallxu.common.exception.GlobalException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ExceptionHandler(value = Exception.class)
    public ResponseBean exceptionHandler(HttpServletRequest request, Exception ex) {

        ResponseBean resp = new ResponseBean();
        //按需重新封装需要返回的错误信息
        if (ex instanceof BindException) {
            BindException exception = (BindException)ex;
            List<ObjectError> allErrors = exception.getAllErrors();
            ObjectError objectError = allErrors.get(0);

            resp.setRespCode(objectError.getDefaultMessage());

        }else if(ex instanceof GlobalException) {
            //抛出的globalException异常
            GlobalException exception = (GlobalException)ex;
            resp.setRespCode(exception.getMessage());

        }
        else {
            resp.setRespCode(ErrorCode.SYS_ERROR);
        }
        return resp;
    }

}
