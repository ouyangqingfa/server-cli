package com.ad.core.system.vo;

import com.ad.core.system.entity.SysUser;

/**
 * 用户信息
 *
 * @author CoderYoung
 */
public class UserVo extends SysUser {

    private String token;
    /**
     * 新建用户时传入此密码
     */
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
