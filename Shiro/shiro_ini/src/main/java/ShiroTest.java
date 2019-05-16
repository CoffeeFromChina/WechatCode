import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;
import java.util.List;

public class ShiroTest {
    public static void main(String[] args) {
        //设置用户
        User p1 = new User();
        p1.setName("p1");
        p1.setPassword("123456");

        User p2 = new User();
        p2 .setName("p2");
        p2 .setPassword("123456");

        User p3 = new User();
        p3.setName("p3");
        p3.setPassword("123456");

        List<User> users = new ArrayList<User>();
        users.add(p1);
        users.add(p2);
        users.add(p3);

        //角色
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("productManager");
        roles.add("orderManager");

        //权限
        List<String> permissions = new ArrayList<String>();
        permissions.add("addProduct");
        permissions.add("deleteProduct");
        permissions.add("addOrder");
        permissions.add("deleteOrder");

        for(User user: users){
            if(login(user)){
                System.out.printf("%s \t登录成功，用的密码是 %s\t %n",user.getName(),user.getPassword());
            }else{
                System.out.printf("%s \t登录失败，用的密码是 %s\t %n",user.getName(),user.getPassword());
            }
        }

        System.out.println("=================================================");

        for(User user: users){
            for(String role:roles) {
                if (login(user)) {
                    if (hasRole(role)) {
                        System.out.printf("%s\t 拥有角色: %s\t%n", user.getName(), role);
                    } else {
                        System.out.printf("%s\t 没有角色: %s\t%n", user.getName(), role);
                    }
                }
            }
        }

        System.out.println("=================================================");

        for(User user: users){
            for(String permission: permissions){
                if(login(user)){
                    if(isPermission(permission)){
                        System.out.printf("%s\t 拥有权限: %s\t%n",user.getName(),permission);
                    }else{
                        System.out.printf("%s\t 没有权限: %s\t%n",user.getName(),permission);
                    }
                }
            }
        }

    }

    //获取主体
    private static Subject  getSubject() {
        //加载配置文件，并获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取安全管理者实例
        SecurityManager sm = factory.getInstance();
        //将安全管理者放入全局对象
        SecurityUtils.setSecurityManager(sm);
        //全局对象通过安全管理者生成Subject对象
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    //登录
    private static boolean login(User user) {
        Subject subject = getSubject();
        //如果已经登录过了，退出
        if(subject.isAuthenticated())
            subject.logout();

        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
        } catch (AuthenticationException e) {
            //验证错误
            return false;
        }
        return subject.isAuthenticated();
    }

    //获取当前主体角色
    private static boolean hasRole(String role) {
        Subject subject = getSubject();
        return subject.hasRole(role);
    }

    //获取当前主体权限
    private static boolean isPermission(String permit) {
        Subject subject = getSubject();
        return subject.isPermitted(permit);
    }
}
