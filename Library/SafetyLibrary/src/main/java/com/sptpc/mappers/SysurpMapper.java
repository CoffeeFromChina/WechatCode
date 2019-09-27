package com.sptpc.mappers;

import com.sptpc.pojo.SysPermission;
import com.sptpc.pojo.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

//非自动生成文件
@Repository
public interface SysurpMapper {
    public List<SysRole> getRole(String rdID);

    public List<SysPermission> getPermission(String roleName);
}
