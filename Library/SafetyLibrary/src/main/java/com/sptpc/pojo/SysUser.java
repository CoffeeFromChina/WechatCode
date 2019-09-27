package com.sptpc.pojo;

import java.io.Serializable;
import java.util.Date;

//系统用户信息相当于shiro的subject
public class SysUser implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String identity;

    private Date date;

    private String photo;

    public SysUser(Integer id, String name, String password, String identity, Date date, String photo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.identity = identity;
        this.date = date;
        this.photo = photo;
    }

    public SysUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
