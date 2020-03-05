package com.wallxu.common.exception;

import com.wallxu.common.constant.ErrorCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/19.
 */
public class GlobalException extends RuntimeException implements Serializable{

    static final long serialVersionUID = 6034897152615645939L;

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
