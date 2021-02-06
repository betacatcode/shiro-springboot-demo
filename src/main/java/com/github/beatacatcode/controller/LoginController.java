package com.github.beatacatcode.controller;

import com.github.beatacatcode.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @PostMapping("/doLogin")
    public String doLogin(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "请输入用户名和密码！";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.info("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.info("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.info("没有权限！", e);
            return "没有权限";
        }

        log.info(user.getUsername()+"登陆成功");
        return "login success";
    }

    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @GetMapping("/error")
    public String error() {
        return "unauthorized";
    }

    @GetMapping("/admin/index")
    public String goAdminIndex(){
        return "adminPage";
    }
}
