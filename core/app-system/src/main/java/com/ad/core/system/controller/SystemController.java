package com.ad.core.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.ad.cache.AppCacheUtil;
import com.ad.common.utils.DateEx;
import com.ad.core.BaseController;
import com.ad.core.BaseResult;
import com.ad.core.system.auth.jwt.JwtUtil;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.utils.UserUtil;
import com.ad.core.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统相关
 *
 * @author CoderYoung
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/api/system")
public class SystemController extends BaseController {

    private RSA rsa;

    @Resource
    private ISysUserService userService;

    public SystemController() {
        this.buildRsa();
    }

    private void buildRsa() {
        this.rsa = new RSA();
    }

    @ApiOperation("刷新RSA密钥")
    @PostMapping("refreshRsa")
    public BaseResult<Boolean> refreshRsaKey() {
        this.buildRsa();
        return buildResult(true);
    }

    /**
     * 获取RSA公钥供前端加密
     *
     * @return RSA公钥
     */
    @ApiOperation("RSA公钥")
    @GetMapping("getRSAKey")
    public BaseResult<String> getRsaKey() {
        return buildResult(rsa.getPublicKeyBase64());
    }

    /**
     * 登录系统
     *
     * @param uid 用户名
     * @param pwd 加密后的密码
     * @return 用户token
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public BaseResult<UserVo> login(@RequestParam("uid") String uid, @RequestBody String pwd) {
        if (StringUtils.isNoneEmpty(uid) && StringUtils.isNotEmpty(pwd)) {
            try {
                String decryptPwd = rsa.decryptStr(pwd, KeyType.PrivateKey);
                SysUser user = userService.getUserByUserId(uid);
                if (user != null) {
                    if (user.getStatus() != 1) {
                        return buildError(-6, "账号状态异常");
                    }
                    String finalPwd = SecureUtil.sha1(decryptPwd + user.getSign());
                    if (user.getPwd().equals(finalPwd)) {
                        UserVo userVo = UserUtil.getUser(user);
                        //重新登录
                        if (StringUtils.isNotBlank(userVo.getToken())) {
                            AppCacheUtil.TOKEN_CACHE.remove(userVo.getToken());
                        }
                        userVo.setToken(JwtUtil.sign(uid));
                        return buildResult(userVo);
                    } else {
                        return buildError(-5, "密码错误");
                    }
                } else {
                    return buildError(-4, "用户不存在");
                }
            } catch (CryptoException e) {
                logger.error(e.getMessage());
                return buildError(-2, "密钥异常");
            } catch (Exception e) {
                logger.error(e.getMessage());
                return buildError(-3, e.getMessage());
            }
        } else {
            return buildError(-1, "检查传参");
        }
    }

    @ApiOperation("注销登录")
    @PostMapping("logout")
    public BaseResult<Boolean> logout(HttpServletRequest request, @RequestParam("uid") String uid) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)) {
            AppCacheUtil.TOKEN_CACHE.remove(token);
        }
        AppCacheUtil.USER_CACHE.remove(uid);
        return buildResult(true);
    }

}
