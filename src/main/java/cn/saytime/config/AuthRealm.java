package cn.saytime.config;

import cn.saytime.model.Module;
import cn.saytime.model.Role;
import cn.saytime.model.User;
import cn.saytime.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 11:45
 * @Description:
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();

        List<String> permissions=new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                Set<Module> modules = role.getModules();
                if (modules.size() > 0) {
                    for(Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo ();
        //将权限放入shiro中
        info.addStringPermissions(permissions);

        return info;
    }

    /**
     * 认证、登录
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUserName(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
