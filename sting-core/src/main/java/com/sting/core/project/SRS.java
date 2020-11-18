package com.sting.core.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * 统一返回对象 / 统一参数接收对象
 */
public class SRS extends JSONObject {

    /**
     * 操作成功-200
     */
    public static SRS bySuccess() {
        return create()
                .code(StMsg.SUCCESS_200.getCode())
                .message(StMsg.SUCCESS_200.getMessage())
                .success(StMsg.SUCCESS_200.isSuccess())
                .data(new JSONObject());
    }

    /**
     * 操作失败-500
     */
    public static SRS byError() {
        return create()
                .code(StMsg.ERROR_500.getCode())
                .message(StMsg.ERROR_500.getMessage())
                .success(StMsg.ERROR_500.isSuccess())
                .data(new JSONObject());
    }

    /**
     * 指定返回消息对象
     */
    public static SRS byResMsg(StResponseMessage resMsg) {
        return create()
                .code(resMsg.getCode())
                .message(resMsg.getMessage())
                .data(new JSONObject());
    }

    public static SRS byResMsg(StResponseMessage response, Object data) {
        return byResMsg(response).data(data);
    }

    public SRS data(Object data) {
        return this.set("data", data);
    }

    public SRS success(boolean success) {
        return this.set("success", success);
    }

    public SRS message(Object message) {
        return this.set("message", message);
    }

    public SRS code(Object code) {
        return this.set("code", code);
    }

    public static SRS byError(Integer code) {
        return byError().code(code);
    }

    public static SRS byError(String message) {
        return byError().message(message);
    }

    public static SRS byError(Object data) {
        return byError().data(data);
    }

    public static SRS byError(Integer code, String message) {
        return byError().code(code).message(message);
    }

    public static SRS byError(Integer code, Object data) {
        return byError().code(code).data(data);
    }

    public static SRS byError(String key, Object value) {
        return byError().set(key, value);
    }

    public static SRS byError(Integer code, String message, Object data) {
        return byError().code(code).data(data).message(message);
    }

    public static SRS bySuccess(String key, Object value) {
        return bySuccess().set(key, value);
    }

    public static SRS bySuccess(Integer code, String message) {
        return bySuccess().code(code).message(message);
    }

    public static SRS bySuccess(Integer code, String message, Object data) {
        return bySuccess().code(code).data(data).message(message);
    }

    public static SRS bySuccess(Integer code, Object data) {
        return bySuccess().code(code).data(data);
    }

    public static SRS bySuccess(Object data) {
        return bySuccess().data(data);
    }

    public static SRS create() {
        return new SRS();
    }

    public static SRS create(String key, Object value) {
        return create().set(key, value);
    }

    /**
     * 复制对象并重新开辟内存空间
     */
    public static JSONObject copyObj(Object object) {
        return JSON.parseObject(JSON.toJSONString(object));
    }

    /**
     * 复制对象并重新开辟内存空间
     */
    public static JSONArray copyArray(Object arrayObject) {
        return JSON.parseArray(JSON.toJSONString(arrayObject));
    }

    /**
     * 复制对象并重新开辟内存空间
     */
    public SRS copy() {
        return JSON.parseObject(this.toJSONString(), SRS.class);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static JSONObject parseObj(Object obj) {
        return JSON.parseObject(JSON.toJSONString(obj));
    }

    public <T> T parse(Class<T> c) {
        return JSON.parseObject(JSON.toJSONString(this), c);
    }

    public <T> List<T> parseArray(Class<T> c) {
        return JSON.parseArray(JSON.toJSONString(this), c);
    }

    public SRS set(String key, Object value) {
        super.put(key, value);
        return this;
    }

    private static String toJsonStringNull(Object o) {
        return JSON.toJSONString(o, SerializerFeature.WriteMapNullValue);
    }
}
