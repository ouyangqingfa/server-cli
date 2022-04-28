package com.ad.core.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ad.cache.AppCacheUtil;
import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.utils.UserUtil;
import com.ad.core.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author CoderYoung
 * Date  2022-04-27
 */
@Api(tags = "用户表")
@RestController
@RequestMapping(value = "/api/system/sysUser")
public class SysUserController extends BizController<ISysUserService, SysUser> {

    @Override
    public BaseResult<Integer> saveOrUpdate(@RequestBody SysUser part) {
        return buildError(-1, "Not Allowed");
    }

    @Override
    public BaseResult<Integer> delete(@RequestParam Serializable id) {
        return buildError(-1, "Not Allowed");
    }

    @ApiOperation("保存用户")
    @PostMapping("save")
    public BaseResult<Boolean> save(@RequestBody UserVo user) {
        UserVo currentUser = UserUtil.getUser();
        if (UserUtil.isAdmin(currentUser)) {
            SysUser existUser = baseService.getUserByUserId(user.getUid());
            SysUser userInfo = Convert.convert(SysUser.class, user);
            //TODO 角色处理
            if (existUser != null) {
                //修改
                if (StringUtils.isNotBlank(user.getPassword())) {
                    String userSign = IdUtil.fastSimpleUUID();
                    userInfo.setSign(userSign);
                    userInfo.setPwd(SecureUtil.sha1(user.getPassword() + userSign));
                }
                userInfo.setId(existUser.getId());
                userInfo.setUpdater(currentUser.getUid());
                userInfo.setUpdateTime(LocalDateTime.now());
            } else {
                //新增
                String userSign = IdUtil.fastSimpleUUID();
                String finalPwd = SecureUtil.sha1(user.getPassword() + userSign);
                userInfo.setStatus(1);
                userInfo.setRegDate(LocalDateTime.now());
                userInfo.setSign(userSign);
                userInfo.setPwd(finalPwd);
                userInfo.setCreator(currentUser.getUid());
                userInfo.setCreateTime(LocalDateTime.now());
            }
            baseService.saveOrUpdate(userInfo);
            return buildResult(true);
        } else {
            return buildError(-999, "没有操作权限");
        }
    }

}