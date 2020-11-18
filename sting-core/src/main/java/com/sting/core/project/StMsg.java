package com.sting.core.project;

/**
 * 服务端消息返回对象
 */
public enum StMsg implements StResponseMessage {

    //系统已经使用的Code
    SUCCESS_200(200, "操作完成", true),

    ERROR_400(400, "请求参数错误", false),
    ERROR_401(401, "身份认证失败", false),
    ERROR_402(402, "权限不足无法访问", false),
    ERROR_404(404, "请求资源不存在", false),

    ERROR_500(500, "操作失败", false),
    ERROR_TIME_OUT_500(500, "连接超时，请检查网络环境", false),
    ERROR_SERVER_503(503, "服务器目前无法使用", false),
    ERROR_OUT_555(555, "强制退出", false),

    RE_SUBMIT_600(600, "重复提交", false);

    private final Integer code;
    private final String message;
    private final boolean success;

    StMsg(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public String toString() {
        return "{" +
                "  \"code\": " + code +
                "  \"message\": " + message +
                "  \"success\": " + success +
                "}";
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
