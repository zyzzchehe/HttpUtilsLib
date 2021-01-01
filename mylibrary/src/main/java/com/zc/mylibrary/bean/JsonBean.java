package com.zc.mylibrary.bean;

/**
 * Created by Tony.Fan on 2018/3/12 16:36
 * 所有需要用 json，FastJson，获取gson的 解析的都需要实现这个，
 */



import com.zc.mylibrary.httpcore.INotProguard;

import java.io.Serializable;

public class JsonBean implements INotProguard, Serializable {
    public JsonBean() {
        super();
    }
}
