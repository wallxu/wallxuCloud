package com.wallxu.common.constant;

/**
 * Created by Administrator on 2018/7/13.
 */
public enum ErrorCode {

    //公共
    SUCCESS(0, "操作成功"),
    SYS_ERROR(99, "操作失败"),
    INVALID_LC(98, "LC无效"),
    SYS_BACKEND_ERROR(97, "查询后台服务失败"),
    SYS_PARA_ISNULL(96, "查询参数不能为空"),
    SYS_AGENT_ISNULL(95, "未找到此代理数据"),
    SYS_INTERFACE_DISABLED(94, "接口停用"),


    //用户模块 10--
    USER_IS_NULL(1001,"此用户不存在"),
    USER_LOGIN_FAIL(1002,"用户名或密码不存在"),
    //商品模块 20--
    //订单模块 30--
    //秒杀模块 40--
    MIAOSHA_ORDER_WAIT(4001,"秒杀排队中");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
