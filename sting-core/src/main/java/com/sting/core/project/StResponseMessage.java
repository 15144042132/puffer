package com.sting.core.project;

/**
 * 信息返回接口
 */
public interface StResponseMessage {
    Integer getCode();

    boolean isSuccess();

    String getMessage();
}
