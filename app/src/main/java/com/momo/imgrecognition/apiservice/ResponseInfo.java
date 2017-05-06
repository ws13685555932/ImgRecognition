package com.momo.imgrecognition.apiservice;

/**
 * Created by Administrator on 2017/5/6.
 */

public class ResponseInfo <T>{
    boolean success;
    String message;
    T data;
    String code;

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
