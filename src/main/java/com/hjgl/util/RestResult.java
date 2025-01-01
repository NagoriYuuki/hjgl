package com.hjgl.util;

import java.util.List;

public class RestResult {
    private int code=0;
    private int count=0;
    private String msg=null;
    private List data;

    public RestResult(int count,List data){
        this.count=count;
        this.data=data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
