/**
 * @file UserToken.java
 * @author Guo Qiuzhong
 * @date 2021/4/20 14:08
 * @copyright 2020-2021 www.bosssoft.com.cn Inc. All rights reserved.
 **/
package com.guo.gymxmjpa.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @class UserToken
 * @description
 * @author Guo Qiuzhong
 * @date 2021/4/20 14:08
 * @version 1.0
 * @see
 * @since
 **/
public class UserToken extends UsernamePasswordToken {
    private String loginType;
    public UserToken() {
    }
    public UserToken(final String username, final String password,
                     final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }
}