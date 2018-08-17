package com.coder.dubbo.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.model.User;
import com.coder.util.MD5Encrypt;
import com.coder.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.coder.util.MD5Encrypt.MD5Encode;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String name,String password){
        Subject subject = SecurityUtils.getSubject();//获取当前用户对象
        if (!subject.isAuthenticated()) {
            // 把用户名和密码封装为UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(name, MD5Encrypt.MD5Encode(MD5Encode(password)));
            token.setRememberMe(true);
            try {
                // 执行登陆
                subject.login(token);
                return "/Main";
            } catch (AuthenticationException ae) {
                System.out.println("登录失败--->" + ae.getMessage());
            }
        }
        return "login";
    }

    @GetMapping
    public String Index(){
        return "index";
    }

    @GetMapping("/GetSession")
    @ResponseBody
    public String getSessionId(HttpServletRequest req){
        Object o = req.getSession().getAttribute("springboot");
        if(o == null){
            o = "端口：" + req.getLocalPort() + "生成SessionId:" + req.getSession().getId();
            req.getSession().setAttribute("springboot", o);
        }
        return o + "<br/>当前端口=" + req.getLocalPort() +  " sessionId=" + req.getSession().getId() +"<br/>";
    }

    @GetMapping("/Main")
    public String main(){
        return "main";
    }

    @GetMapping("/LoveDongQing")
    public String loveDongQing(){
        return "dongqing";
    }
}
