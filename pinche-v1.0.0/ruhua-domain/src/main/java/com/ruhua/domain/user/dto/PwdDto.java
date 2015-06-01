package com.ruhua.domain.user.dto;

import com.ruhua.domain.Validatable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: lijing3
 * Date: 14-11-8
 * Time: 下午2:07
 * To change this template use File | Settings | File Templates.
 */
public class PwdDto implements Serializable,Validatable {
    private Integer id;
    private String userName;
    private String email;
    private String oldPass2Md5;
    private String newPass2Md5;
    private String newPass2Md52;
    public boolean val() {
        if(oldPass2Md5 == null || newPass2Md5 == null ) {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPass2Md5() {
        return oldPass2Md5;
    }

    public void setOldPass2Md5(String oldPass2Md5) {
        this.oldPass2Md5 = oldPass2Md5;
    }

    public String getNewPass2Md5() {
        return newPass2Md5;
    }

    public void setNewPass2Md5(String newPass2Md5) {
        this.newPass2Md5 = newPass2Md5;
    }

    public String getNewPass2Md52() {
        return newPass2Md52;
    }

    public void setNewPass2Md52(String newPass2Md52) {
        this.newPass2Md52 = newPass2Md52;
    }
}
