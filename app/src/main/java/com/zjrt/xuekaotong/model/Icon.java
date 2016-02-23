package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2015/12/30.
 */
public class Icon {
    private String title;
    private String icon_num;
    private String exchanged_num;
    private String price;
    private String image_url;

    public Icon(String title, String icon_num, String exchanged_num, String price) {
        this.title = title;
        this.icon_num = icon_num;
        this.exchanged_num = exchanged_num;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon_num() {
        return icon_num;
    }

    public void setIcon_num(String icon_num) {
        this.icon_num = icon_num;
    }

    public String getExchanged_num() {
        return exchanged_num;
    }

    public void setExchanged_num(String exchanged_num) {
        this.exchanged_num = exchanged_num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
