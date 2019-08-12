package com.example.demo.common.config;

/**
 * 全局通用返回
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/8
 */
public class CommonResult {
    public static final CommonResult SUCCESS = (new CommonResult()).setMsg("保存成功!");
    private boolean success = true;
    private String code;
    private String msg;
    private Object result;

    public CommonResult() {
    }

    public CommonResult(Object data) {
        this.result = data;
    }

    public String getCode() {
        return this.code;
    }

    public CommonResult setCode(String errorcode) {
        this.code = errorcode;
        return this;
    }

    public CommonResult setFailed() {
        this.success = false;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public CommonResult setMsg(String umsg) {
        this.msg = umsg;
        return this;
    }

    public Object getResult() {
        return this.result;
    }

    public CommonResult setResult(Object data) {
        this.result = data;
        return this;
    }
}
