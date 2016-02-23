package com.zjrt.xuekaotong.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class OneFenlei implements Serializable{
    private String id;
    private String categoryName;
    private List<TwoFenlei> list;

    public OneFenlei(String id, String categoryName, List<TwoFenlei> list) {
        this.id = id;
        this.categoryName = categoryName;
        this.list = list;
    }

    public OneFenlei(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<TwoFenlei> getList() {
        return list;
    }

    public void setList(List<TwoFenlei> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OneFenlei{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", list=" + list +
                '}';
    }
}
