/**
 * @file UserController.java
 * @author Guo Qiuzhong
 * @date 2021/4/20 11:58
 * @copyright 2020-2021 www.bosssoft.com.cn Inc. All rights reserved.
 **/
package com.guo.gymxmjpa.controller;

import com.guo.gymxmjpa.dao.MenberDao;
import com.guo.gymxmjpa.entity.Adminuser;
import com.guo.gymxmjpa.entity.Member;
import com.guo.gymxmjpa.service.MenberDaoImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @class UserController
 * @description
 * @author Guo Qiuzhong
 * @date 2021/4/20 11:58
 * @version 1.0
 * @see
 * @since
 **/
@Controller
@RequestMapping("/member")
public class UserController {
    @Autowired
    private MenberDao menberDao;

    /**
     * @Description: 用户登录验证方法
     */
    @RequestMapping("/dl")
    public String login(String username, String password, HttpSession httpSession, Model model){
       /* Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken userToken=new UsernamePasswordToken(username,password);*/
        try{
            /*subject.login(userToken);*/
            Member a= menberDao.findByUsernameAndPassword(username,password);
            if (null==a){
                return "login1";
            }
            httpSession.setAttribute("user",a);
            return "WEB-INF/jsp/index1";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名或密码错误,请重新输入");
            return "login1";
        }

        /*Adminuser a= adminuserDao.findByAdminNameAndAdminmima(username,password);
        if(a!=null){
            httpSession.setAttribute("user",a);
            return "WEB-INF/jsp/index" ;
        }
        model.addAttribute("mag","账号或密码错误");
        return "login";*/
    }

    /**
     * @Description: 退出登录后清楚session
     */
    @RequestMapping("/logout")
    public String logout(){
        return "login1";

    }

    /**
     * @Description: 跳转到修改密码界面
     */
    @RequestMapping("/updPassword")
    public String updPassword(){
        return "WEB-INF/jsp/updPassword1";
    }


    /**
     * @Description: 修改密码
     */
    @RequestMapping("/upd/updPassword")
    public String updPasswordConfirm(String oldPassword,String newPassword,String newPasswordAgain,HttpSession httpSession,Model model){
        /*Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!.%*#?&])[A-Za-z\\d$@$!.%*#?&]{8,}$");
        Matcher m = p.matcher(newPassword);
        if(!m.matches()){
            model.addAttribute("msg","新密码最少为8位并为字母+数字+特殊字符");
            return "WEB-INF/jsp/updPassword1";
        }*/
        if(!newPassword.equals(newPasswordAgain)){
            model.addAttribute("msg","两次输入新密码不一致,请重新输入");
            return "WEB-INF/jsp/updPassword1";
        }
        Member a =(Member) httpSession.getAttribute("user");
        if(null != a){
            if(!a.getPassword().equals(oldPassword)){
                model.addAttribute("msg","原密码不正确,请重新输入");
                return "WEB-INF/jsp/updPassword1";
            }
            menberDao.updPassword(a.getMemberId(), newPassword);
        }
        return "redirect:/login1.jsp";
    }
}