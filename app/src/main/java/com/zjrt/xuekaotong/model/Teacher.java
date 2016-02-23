package com.zjrt.xuekaotong.model;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 * 名师推荐实体类
 */
public class Teacher {
    private String id;
    private String name;//名称
    private String type;//类别
    private boolean isReal;//是否认证
    private boolean isPro;//是否专家
    private int year;//教龄
    private String pototoUrl="";//图片url
    private String major;
    private String desc;
    private List<Video> list;

    public Teacher(String name, int year, String major, String desc, String pototoUrl, List<Video> list) {
        this.name = name;
        this.year = year;
        this.major = major;
        this.desc = desc;
        this.pototoUrl = pototoUrl;
        this.list = list;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Video> getList() {
        return list;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public boolean isPro() {
        return isPro;
    }

    public void setIsPro(boolean isPro) {
        this.isPro = isPro;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPototoUrl() {
        return pototoUrl;
    }

    public void setPototoUrl(String pototoUrl) {
        this.pototoUrl = pototoUrl;
    }

    public Teacher(String name, String type, boolean isReal) {
        this.name = name;
        this.type = type;
        this.isReal = isReal;
    }

    public Teacher(String id, String name, String pototoUrl) {
        this.id = id;
        this.name = name;
        this.pototoUrl = pototoUrl;
    }

    public Teacher(String id, String name, String pototoUrl, String major) {
        this.id = id;
        this.name = name;
        this.pototoUrl = pototoUrl;
        this.major = major;
    }

    public Teacher(String name, String major, int year, String pototoUrl, String desc) {
        this.name = name;
        this.major = major;
        this.year = year;
        this.pototoUrl = pototoUrl;
        this.desc = desc;
    }

    public Teacher(String name, String type, boolean isReal, boolean isPro, int year, String pototoUrl) {
        this.name = name;
        this.type = type;
        this.isReal = isReal;
        this.isPro = isPro;
        this.year = year;
        this.pototoUrl = pototoUrl;
    }

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setIsReal(boolean isReal) {
        this.isReal = isReal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isReal=" + isReal +
                '}';
    }
}
