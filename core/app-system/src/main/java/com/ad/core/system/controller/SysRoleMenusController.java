package com.ad.core.system.controller;

import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.core.PageResult;
import com.ad.core.system.service.ISysRoleMenusService;
import com.ad.core.system.entity.SysRoleMenus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色菜单
 *
 * @author CoderYoung
 * Date  2022-04-20
 */
@Api(tags = "角色菜单")
@RestController
@RequestMapping(value = "/api/system/sysRoleMenus")
public class SysRoleMenusController extends BizController<ISysRoleMenusService, SysRoleMenus> {

    @ApiOperation("保存角色菜单")
    @PostMapping("save")
    public BaseResult<Boolean> saveRoleMenus(@RequestParam String roleId, @RequestBody List<SysRoleMenus> menus) {
        baseService.remove(new QueryWrapper<SysRoleMenus>().eq("role_id", roleId));
        if (menus != null && menus.size() > 0) {
            for (SysRoleMenus menu : menus) {
                if (StringUtils.isBlank(menu.getRoleId())) {
                    menu.setRoleId(roleId);
                    menu.setStatus(1);
                }
            }
            baseService.saveBatch(menus);
        }
        return buildResult(true);
    }

    @ApiOperation("获取角色菜单")
    @PostMapping("getRoleMenus")
    public PageResult<SysRoleMenus> getRoleMenus(String roleId) {
        List<SysRoleMenus> roleMenus = baseService.list(new QueryWrapper<SysRoleMenus>().eq("role_id", roleId));
        return buildResult(roleMenus);
    }

}