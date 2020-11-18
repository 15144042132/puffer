package com.sting.core.project;

/**
 * 自定异常处理
 */
public class StException extends RuntimeException {
    //错误代码
    public Integer code = 500;
    //消息内容
    public String message = "";
    //消息内容
    public boolean success = false;

    public StException(StResponseMessage miResponseMsg) {
        this.message = miResponseMsg.getMessage();
        this.code = miResponseMsg.getCode();
        this.success = miResponseMsg.isSuccess();
    }

    public StException(String message) {
        this.message = message;
    }

    public StException(Integer code) {
        this.code = code;
    }

    public StException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public SRS getResponse() {
        return SRS.byError(code, message);
    }
}
