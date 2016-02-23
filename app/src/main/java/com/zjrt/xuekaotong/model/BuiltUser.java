package com.zjrt.xuekaotong.model;

/**
 * Created by Administrator on 2016/1/28.
 * 封装向能力天空请求视频播放、下载等功能的用户名和密码
 */
public class BuiltUser {

    static String BUILTUSERNAME = "18600895117";
    static String BUILTUSERPASSWORD = "a7777777";

    public BuiltUser() {
    }

    public String getBUILTUSERNAME() {
        return BUILTUSERNAME;
    }

    public void setBUILTUSERNAME(String BUILTUSERNAME) {
        this.BUILTUSERNAME = BUILTUSERNAME;
    }

    public String getBUILTUSERPASSWORD() {
        return BUILTUSERPASSWORD;
    }

    public void setBUILTUSERPASSWORD(String BUILTUSERPASSWORD) {
        this.BUILTUSERPASSWORD = BUILTUSERPASSWORD;
    }
}
