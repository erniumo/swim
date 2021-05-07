/**
 * @file UserRealm.java
 * @author Guo Qiuzhong
 * @date 2021/4/20 13:52
 * @copyright 2020-2021 www.bosssoft.com.cn Inc. All rights reserved.
 **//*

package com.guo.gymxmjpa.real;

import com.guo.gymxmjpa.dao.MenberDao;
import com.guo.gymxmjpa.entity.Adminuser;
import com.guo.gymxmjpa.entity.Member;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.copyValueOf;

*/
/**
 * @class UserRealm
 * @description
 * @author Guo Qiuzhong
 * @date 2021/4/20 13:52
 * @version 1.0
 * @see
 * @since
 **//*

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private MenberDao menberDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("已授权");
        Subject subject = SecurityUtils.getSubject();
        Member user = (Member) principalCollection.getPrimaryPrincipal() ;
        //
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken ;
        Member member = menberDao.findByUsernameAndPassword(token.getUsername(),String.copyValueOf(token.getPassword()));

        if(null == member){
            return null ;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(member.getUsername(),member.getPassword(),"") ;
        System.out.println("完成认证！");
        return info;
    }
}*/
