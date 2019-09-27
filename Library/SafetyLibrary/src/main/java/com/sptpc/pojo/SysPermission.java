package com.sptpc.pojo;

import java.io.Serializable;

//权限
public class SysPermission implements Serializable {
    private Integer ID;

    private String perName;

    public SysPermission(Integer ID, String perName) {
        this.ID = ID;
        this.perName = perName;
    }

    public SysPermission() {
        super();
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer ID) {
        this.ID = ID;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }
}
