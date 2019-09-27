package com.sptpc.realm;

import com.sptpc.mappers.SysurpMapper;
import com.sptpc.mappers.UserLoginMapper;
import com.sptpc.pojo.SysPermission;
import com.sptpc.pojo.SysRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//自定义realm，权限和登录校验，继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    public UserLoginMapper userLoginMapper;
    @Autowired
    public SysurpMapper sysurpMapper;

    //角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前主体的身份
        Object username = principals.getPrimaryPrincipal();

        //获取当前主体的角色信息
        List<SysRole> roleList = sysurpMapper.getRole((String) username);

        List<SysPermission> permissionList = null;
        List<String> permission = new ArrayList<String>();
        List<String> role = new ArrayList<String>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //遍历角色信息，并将权限和角色添加到相应的 List 中
        for (SysRole sysRole : roleList) {
            permissionList = sysurpMapper.getPermission(sysRole.getRoleName());
            for (SysPermission p : permissionList) {
                permission.add(p.getPerName());
            }
            role.add(sysRole.getRoleName());
        }
        info.addRoles(role);
        info.addStringPermissions(permission);
        return info;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录的主体的身份
        Object username = token.getPrincipal();

        if (username != null) {
            Object password = (Object) userLoginMapper.findpwdByName((String) username);
            if (password == null) {
                throw new IncorrectCredentialsException("密码错误");
            } else {
                //对比密码
                AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, "realm1");
                return info;
            }
        } else {
            throw new UnknownAccountException("无用户");
        }
    }
}
