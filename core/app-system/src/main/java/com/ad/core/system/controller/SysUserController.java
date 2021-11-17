package com.ad.core.system.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.SysUserService;
import com.ad.core.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author CoderYoung
 * @since 2021-11-12
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/api/system/sysUser")
public class SysUserController extends BizController<SysUserService, SysUser> {

    @ApiOperation("创建用户")
    @PostMapping("create")
    public BaseResult<Boolean> create(@RequestBody UserVo user) {
        SysUser existUser = baseService.getUserByUserId(user.getUid());
        if (existUser == null) {
            SysUser dbUser = Convert.convert(SysUser.class, user);
            String userSign = IdUtil.fastSimpleUUID();
            String finalPwd = SecureUtil.sha1(user.getPassword() + userSign);
            dbUser.setStatus(1);
            dbUser.setRegDate(LocalDateTime.now());
            dbUser.setSign(userSign);
            dbUser.setPwd(finalPwd);
            dbUser.insertOrUpdate();
            return buildResult(true);
        } else {
            return buildError("用户名已存在");
        }
    }
}

