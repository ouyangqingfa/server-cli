package com.ad.core.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author CoderYoung
 * Date  2022-04-20
 */
@Api(tags = "用户")
@RestController
@RequestMapping(value = "/api/system/sysUser")
public class SysUserController extends BizController<ISysUserService, SysUser> {

    @Override
    public BaseResult<Integer> saveOrUpdate(@RequestBody SysUser part) {
        return buildError(-1, "Not Allowed");
    }

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
            baseService.saveOrUpdate(dbUser);
            return buildResult(true);
        } else {
            return buildError("用户名已存在");
        }
    }

    @ApiOperation("修改账号密码（管理员权限）")
    @PostMapping("resetUserPassword")
    public BaseResult<Boolean> resetUserPassword(@RequestBody UserVo user) {
        SysUser dbUser = baseService.getUserByUserId(user.getUid());
        if (dbUser != null) {
            String userSign = IdUtil.fastSimpleUUID();
            dbUser.setSign(userSign);
            dbUser.setPwd(SecureUtil.sha1(user.getPassword() + userSign));
            baseService.saveOrUpdate(dbUser);
            return buildResult(true);
        } else {
            return buildError("用户名不存在");
        }
    }

}