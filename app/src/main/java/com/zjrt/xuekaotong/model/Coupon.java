package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2016/1/4.
 */
public class Coupon {
    private String type;
    private String value;
    private String max_num;
    private String time;

    public Coupon(String type, String value, String max_num, String time) {
        this.type = type;
        this.value = value;
        this.max_num = max_num;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMax_num() {
        return max_num;
    }

    public void setMax_num(String max_num) {
        this.max_num = max_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
