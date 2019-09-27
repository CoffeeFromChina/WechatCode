package com.sptpc.pojo;

import java.io.Serializable;

//角色和权限
public class SysRP implements Serializable {
    private Integer ID;

    private String roleName;

    private String perName;

    public SysRP(Integer ID, String roleName, String perName) {
        this.ID = ID;
        this.roleName = roleName;
        this.perName = perName;
    }

    public SysRP() {
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

    public void setRoleName(String rolename) {
        this.roleName = rolename == null ? null : rolename.trim();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String pername) {
        this.perName = pername == null ? null : pername.trim();
    }
}
