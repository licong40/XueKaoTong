package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Course {
    private String id;//课程id
    private String title;//课程名称
    private String size;//课程大小

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Course() {
    }

    public Course(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Course(String id, String title, String size) {
        this.id = id;
        this.title = title;
        this.size = size;
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
}
