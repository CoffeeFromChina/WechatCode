package com.sptpc.mappers;

import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.Reader;
import com.sptpc.pojo.SysUR;
import com.sptpc.pojo.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

//非自动生成文件
//用户登陆的接口
@Repository
public interface UserLoginMapper {

    //查找密码
    public String findpwdByName(String name);

    //更新密码
    public int updatepwdByName(SysUser sysUser);

    //读者表插入一条信息，则在系统用户表中也要插入一条信息
    public int insertSysUser(Reader reader);

    //读者表更新，系统表也要更新
    public int updateSysUser(Reader reader);

    //读者表删除，系统表也要删除
    public int deleteSysUser(String id);

    //查询自己的而用户信息
    public Reader selectSysUser(String id);

    //更新密码
    public int updatePwd(SysUser user);

    //插入新的权限
    public int insertPermission(SysUR sysUR);

    //查询权限
    public List<SysUR> selectPermission(Parameter parameter);

    //删除权限
    public int deletePermission(SysUR sysUR);

    //获取权限数量
    public int getCountsPermission();
}
