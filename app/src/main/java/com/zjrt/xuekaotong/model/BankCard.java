package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2015/12/29.
 */
public class BankCard {
    private String cardImg;//银行卡略缩图
    private String num;//银行卡号
    private String bank;//所属银行

    public BankCard(String bank, String num, String cardImg) {
        this.bank = bank;
        this.num = num;
        this.cardImg = cardImg;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
