package com.sptpc.pojo;

import java.io.Serializable;

//用户和角色
public class SysUR implements Serializable {
    private Integer ID;

    private String userName;

    private String roleName;

    public SysUR(Integer ID, String userName, String roleName) {
        this.ID = ID;
        this.userName = userName;
        this.roleName = roleName;
    }

    public SysUR() {
        super();
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username == null ? null : username.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}
