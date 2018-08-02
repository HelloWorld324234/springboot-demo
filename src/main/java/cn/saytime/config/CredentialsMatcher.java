package cn.saytime.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 14:14
 * @Description:
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
//      获取用户输入的密码
        String password = new String(usernamePasswordToken.getPassword());
//      获取token中的密码
        String dbPasswqord = (String) info.getCredentials();
//      进行密码对比
        return this.equals(password,dbPasswqord);
    }
}
