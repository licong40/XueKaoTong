package com.zjrt.xuekaotong.model;

import com.zjrt.xuekaotong.ablesky.TypeDef;

/**
 * Created by Administrator on 2015/12/28.
 */
public class Charge {
    private int type;//0:提现；1：退款至余额；2：消费；3：充值
    private String  cash;//余额
    private String date;
    private String num;

    public Charge(int type, String cash, String date, String num) {
        this.type = type;
        this.cash = cash;
        this.date = date;
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
