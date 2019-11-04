package com.cse.haste.model.dto;

import com.cse.haste.util.StatusCode;

/**
 * @param <T> 类型参数
 * @author WangZhenqi
 */
public class Response<T> {
    /**
     * 响应状态码
     */
    private int statusCode;
    /**
     * 响应数据
     */
    private T data;

    public Response() {
        this.statusCode = StatusCode.SUCCESS;
        this.data = null;
    }

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(T data) {
        this.statusCode = StatusCode.SUCCESS;
        this.data = data;
    }

    public Response(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }
}
