package com.zjrt.xuekaotong.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/16.
 */
public class Video implements Serializable {
    private String id;//视频id
    private String title;//视频名称
    private String price;//价格
    private String coursePhoto;//图片url
    private String teacherName;//老师名字
    private String category_id;
    private String click_url;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public Video(String id, String title, String coursePhoto, String teacherName,String click_url,String category_id) {
        this.id = id;
        this.title = title;
        this.coursePhoto = coursePhoto;
        this.teacherName = teacherName;
        this.click_url = click_url;
        this.category_id = category_id;
    }
    public Video(String id, String title, String coursePhoto, String teacherName) {
        this.id = id;
        this.title = title;
        this.coursePhoto = coursePhoto;
        this.teacherName = teacherName;
    }

    public String getTeacher() {
        return teacherName;
    }

    public void setTeacher(String teacher) {
        this.teacherName = teacher;
    }

    public Video(String id, String title, String coursePhoto) {
        this.id = id;
        this.title = title;
        this.coursePhoto = coursePhoto;
    }


    public Video(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoursePhoto() {
        return coursePhoto;
    }

    public void setCoursePhoto(String coursePhoto) {
        this.coursePhoto = coursePhoto;
    }


}
