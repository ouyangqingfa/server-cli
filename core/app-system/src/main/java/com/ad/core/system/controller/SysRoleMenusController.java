package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysRoleMenusService;
import com.ad.core.system.entity.SysRoleMenus;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 角色菜单
* @author CoderYoung
* Date  2022-04-20
*/
@Api(tags = "角色菜单")
@RestController
@RequestMapping(value = "/api/system/sysRoleMenus")
public class SysRoleMenusController extends BizController<ISysRoleMenusService,SysRoleMenus> {

}