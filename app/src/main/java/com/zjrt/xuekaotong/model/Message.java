package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2015/11/18.
 */
public class Message {
    private String recTime;//接收时间
    private String content;//消息内容

    public Message() {
    }

    public Message(String recTime, String content) {
        this.recTime = recTime;
        this.content = content;
    }

    public String getRecTime() {
        return recTime;
    }

    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
