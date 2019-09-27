package com.sptpc.pojo;

import java.io.Serializable;

//角色
public class SysRole implements Serializable {
    private Integer ID;

    private String roleName;

    public SysRole(Integer ID, String roleName) {
        this.ID = ID;
        this.roleName = roleName;
    }

    public SysRole() {
        super();
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
}
