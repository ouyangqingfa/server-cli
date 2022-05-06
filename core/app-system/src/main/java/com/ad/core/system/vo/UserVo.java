package com.ad.core.system.vo;

import cn.hutool.core.bean.BeanUtil;
import com.ad.core.system.entity.SysUser;

import java.util.List;

/**
 * 用户信息
 *
 * @author CoderYoung
 */
public class UserVo extends SysUser {

    public UserVo() {
    }

    public UserVo(SysUser sysUser) {
        BeanUtil.copyProperties(sysUser, this);
    }

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


    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
