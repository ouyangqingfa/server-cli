package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.PageResult;
import com.ad.core.system.entity.SysMenus;
import com.ad.core.system.service.ISysUserMenusService;
import com.ad.core.system.entity.SysUserMenus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户额外的菜单权限
 *
 * @author CoderYoung
 * Date  2022-04-20
 */
@Api(tags = "用户菜单")
@RestController
@RequestMapping(value = "/api/system/sysUserMenus")
public class SysUserMenusController extends BizController<ISysUserMenusService, SysUserMenus> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation("获取用户菜单")
    @PostMapping("userMenus")
    public PageResult<SysMenus> getUserMenus() {
        //先获取角色菜单
        //再获取用户菜单
        //去重
        return null;
    }

}