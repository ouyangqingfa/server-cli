package com.ad.core.system.auth.shiro;

import com.ad.cache.AppCacheUtil;
import com.ad.core.system.auth.jwt.JwtToken;
import com.ad.core.system.auth.jwt.JwtUtil;
import com.ad.core.system.common.Constant;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CoderYoung
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private ISysUserService userService;

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = JwtUtil.getClaim(principalCollection.toString(), Constant.ACCOUNT);
        if (account == null) {
            throw new AuthenticationException("无效的token");
        }
        SysUser userInfo = userService.getUserByUserId(account);
        if (userInfo == null) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }

        if (userInfo.getStatus() != 1) {
            throw new AuthenticationException("账号状态异常");
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        // 帐号为空
        if (StringUtils.isBlank(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }

        if (JwtUtil.verify(token) == 2) {
            throw new AuthenticationException("token已经过期");
        } else if (JwtUtil.verify(token) == -1) {
            throw new AuthenticationException("用户名或者密码错误");
        }

        //加入缓存用户信息
        SysUser userInfo = (SysUser) AppCacheUtil.USER_CACHE.get(account);
        if (userInfo == null) {
            userInfo = userService.getUserByUserId(account);
            if (userInfo != null) {
                AppCacheUtil.USER_CACHE.put(account, userInfo);
            }
        }
        if (userInfo == null) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }

        if (userInfo.getStatus() != 1) {
            throw new AuthenticationException("账号状态异常");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

}
