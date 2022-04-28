package com.ad.core.system.auth.shiro;

import com.ad.core.system.auth.jwt.JwtToken;
import com.ad.core.system.auth.jwt.JwtUtil;
import com.ad.core.system.common.Constant;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.utils.UserUtil;
import com.ad.core.system.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
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
        UserVo userInfo = UserUtil.getUser(account);
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
            throw new UnknownAccountException("Token中帐号为空(The account in Token is empty.)");
        }

        int verify = JwtUtil.verify(token);
        if (verify == 2) {
            throw new ExpiredCredentialsException("token已经过期");
        } else if (verify == -1) {
            throw new AuthenticationException("用户名或者密码错误");
        }
        SysUser userInfo = UserUtil.getUser(account);
        if (userInfo == null) {
            throw new AccountException("该帐号不存在(The account does not exist.)");
        }

        if (userInfo.getStatus() != 1) {
            throw new AccountException("账号状态异常");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

}
