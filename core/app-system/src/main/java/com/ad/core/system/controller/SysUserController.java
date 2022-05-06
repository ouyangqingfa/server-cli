package com.ad.core.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.ad.core.BaseController;
import com.ad.core.BaseResult;
import com.ad.core.PageResult;
import com.ad.core.system.entity.SysUser;
import com.ad.core.system.entity.SysUserRole;
import com.ad.core.system.service.ISysUserRoleService;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.utils.UserUtil;
import com.ad.core.system.vo.UserParamVo;
import com.ad.core.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 用户表
 *
 * @author CoderYoung
 * Date  2022-04-27
 */
@Api(tags = "用户表")
@RestController
@RequestMapping(value = "/api/system/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysUserService userService;

    @ApiOperation("保存用户")
    @PostMapping("save")
    public BaseResult<Boolean> save(@RequestParam Boolean create, @RequestBody UserVo user) {
        UserVo currentUser = UserUtil.getUser();
        if (UserUtil.isAdmin(currentUser)) {
            SysUser existUser = userService.getUserByUserId(user.getUid());
            SysUser userInfo = Convert.convert(SysUser.class, user);
            if (StringUtils.isEmpty(user.getUname())) {
                userInfo.setUname(userInfo.getUid());
            }
            if (existUser != null) {
                if (create) {
                    return buildError(-1, "用户已存在");
                }
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
                if (!create) {
                    return buildError(-2, "用户不存在");
                }
                if (StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUid())) {
                    return buildError(-3, "用户名或密码为空");
                }
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

            userRoleService.delUserRoles(user.getUid());
            if (ArrayUtil.isNotEmpty(user.getRoles())) {
                userRoleService.saveBatch(user.getRoles().stream().map(r -> new SysUserRole() {{
                    setUid(user.getUid());
                    setRoleId(r);
                    setStatus(1);
                    setCreator(currentUser.getUid());
                    setCreateTime(LocalDateTime.now());
                }}).collect(Collectors.toList()));
            }
            userService.saveOrUpdate(userInfo);
            return buildResult(true);
        } else {
            return buildError(-99, "没有操作权限");
        }
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("getUserPage")
    public PageResult<UserVo> getUserPage(@RequestParam Integer current,
                                          @RequestParam Integer pageSize,
                                          @RequestBody(required = false) UserParamVo params) {
        return buildResult(userService.getUserPage(current, pageSize, params));
    }

}