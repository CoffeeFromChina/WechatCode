package com.sptpc.controller;

import com.sptpc.mappers.UserLoginMapper;
import com.sptpc.pojo.Parameter;
import com.sptpc.pojo.Reader;
import com.sptpc.pojo.SysUR;
import com.sptpc.pojo.SysUser;
import com.sptpc.realm.UserRealm;
import com.sptpc.util.IdentityCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


//该类的作用主要是处理用户登陆，密码修改，信息维护，登陆验证等功能的一个controller的类
@Controller
@RequestMapping("/Userlogin")
public class UserLoginController {
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserRealm userRealm;

    //产生验证码
    @RequestMapping("/identity")
    public void createIdentity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityCode.outputIdentity(request, response);
    }

    //登陆验证
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(HttpServletRequest request, Model model,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "identity", required = false) String identity) {

        if (identity.equals(request.getSession().getAttribute("randomString"))) {

            System.out.println("用户：" + username);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            //登录
            if (!subject.isAuthenticated()) {
                subject.login(token);
            }
        } else {
            return "login";
        }
        request.getSession().setAttribute("admin", username);
        //将登陆的用户信息存储在session中
        Reader reader = userLoginMapper.selectSysUser(username);
        request.getSession().setAttribute("SysReader", reader);
        //model.addAttribute("admin",user.getUsername());
        return "frame/frameSet";
    }

    //返回后台登陆界面
    @RequestMapping("/index")
    public String login() {
        return "login";
    }

    //用户修改密码
    @RequestMapping(value = "/pwdEdit", method = RequestMethod.POST)
    public String passwordEdit(SysUser user) throws Exception {
        int i = 0;
        user.setDate(new Date());
        i = userLoginMapper.updatepwdByName(user);
        userLoginMapper.updatePwd(user);
        if (i == 0) {
            throw new Exception("密码修改失败");
        }
        return "frame/mainFrame";
    }

    //*********************************************************
    //*                     权限设置                          *
    //*********************************************************

    //进入权限注册界面
    @RequestMapping("toReaderPermission")
    @RequiresPermissions(value = {"permission:toReaderPermission", "iterm:all"}, logical = Logical.OR)
    public String comePermission() {
        return "WEB-INF/readerJsp/readerPermission";
    }

    //插入权限
    @RequestMapping(value = "insertPermission", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions(value = {"permission:insertPermission", "iterm:all"}, logical = Logical.OR)
    public String InsertPermission(SysUR sysUR, Parameter parameter, Model model) {
        userLoginMapper.insertPermission(sysUR);
        int count = userLoginMapper.getCountsPermission();
        int start = parameter.getStart();
        if (start < 0) start = 0;
        if (start > count) start -= 10;
        int end = start + 10;
        parameter.setEnd(end);
        parameter.setStart(start);
        List<SysUR> permission = userLoginMapper.selectPermission(parameter);
        model.addAttribute("permission", permission);
        model.addAttribute("parameter", parameter);
        return "WEB-INF/readerJsp/permissionPage";
    }

    //遍历信息
    @RequestMapping(value = "selectPermission")
    @RequiresPermissions(value = {"permission:selectPermission", "iterm:all"}, logical = Logical.OR)
    public String selectPermission(Parameter parameter, Model model) {
        int count = userLoginMapper.getCountsPermission();
        int start = parameter.getStart();
        if (start < 0) start = 0;
        if (start > count) start -= 10;
        int end = start + 10;
        parameter.setEnd(end);
        parameter.setStart(start);
        List<SysUR> permission = userLoginMapper.selectPermission(parameter);
        model.addAttribute("permission", permission);
        model.addAttribute("parameter", parameter);
        return "WEB-INF/readerJsp/permissionPage";
    }

    //删除权限
    @RequestMapping(value = "deletePermission")
    @RequiresPermissions(value = {"permission:deletePermission", "iterm:all"}, logical = Logical.OR)
    public String DeletePermission(Parameter parameter, RedirectAttributes ra, SysUR sysUR) {
        userLoginMapper.deletePermission(sysUR);
        ra.addAttribute("start", parameter.getStart());
        return "redirect:/Userlogin/selectPermission";
    }

}
