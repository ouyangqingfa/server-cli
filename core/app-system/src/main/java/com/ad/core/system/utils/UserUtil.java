package com.ad.core.system.utils;

import com.ad.cache.AppCacheUtil;
import com.ad.common.utils.AppContext;
import com.ad.common.web.Servlets;
import com.ad.core.system.auth.jwt.JwtUtil;
import com.ad.core.system.common.Constant;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.service.impl.SysUserServiceImpl;
import com.ad.core.system.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Lazy;

/**
 * @author CoderYoung
 */
@Lazy
public class UserUtil {

    private static final ISysUserService userService = AppContext.getBean(SysUserServiceImpl.class);

    public static UserVo getUser(String account) {
        UserVo userInfo = (UserVo) AppCacheUtil.USER_CACHE.get(account);
        if (userInfo == null) {
            userInfo = getUser(userService.getUserByUserId(account));
        }
        return userInfo;
    }

    public static UserVo getUser(SysUser sysUser) {
        UserVo userInfo = null;
        if (sysUser != null) {
            userInfo = new UserVo(sysUser);
            //TODO 添加其他信息（权限-菜单列表）
            AppCacheUtil.USER_CACHE.put(sysUser.getUid(), userInfo);
        }
        return userInfo;
    }


    /**
     * 获取当前会话用户的token
     *
     * @return
     */
    public static String getToken() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Object token = subject.getPrincipal();
            if (token != null) {
                return token.toString();
            } else if (StringUtils.isNotEmpty(Servlets.getRequest().getHeader("Authorization"))) {
                return Servlets.getRequest().getHeader("Authorization");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前会话用户信息
     *
     * @return
     */
    public static UserVo getUser() {
        String token = getToken();
        if (StringUtils.isNotBlank(token)) {
            String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
            if (StringUtils.isNotBlank(account)) {
                return getUser(account);
            }
        }
        return null;
    }

    /**
     * 是否为系统管理员
     *
     * @return
     */
    public static boolean isAdmin() {
        return isAdmin(getUser());
    }

    public static boolean isAdmin(UserVo user) {
        if (user != null) {
            //TODO 根据角色列表判断
            if (user.getUid().equalsIgnoreCase(Constant.ADMIN_ACCOUNT)) {
                return true;
            }
        }
        return false;
    }


}
