package cn.saytime.controller;

import cn.saytime.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 14:27
 * @Description:
 */
@Controller
@Api(description = "登录认证")
public class LoginController {

    @ApiOperation("登录")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation("输入用户名和密码")
    @PostMapping("/loginUser")
    public String loginUser(String username, String password, HttpSession session, Model model) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            model.addAttribute("user",user);
            return "index";
        } catch(Exception e) {
            return "login";//返回登录页面
        }

    }

    @ApiOperation("注销")
    @GetMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "login";
    }
}
