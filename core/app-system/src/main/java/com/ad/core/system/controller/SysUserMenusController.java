package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysUserMenusService;
import com.ad.core.system.entity.SysUserMenus;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 用户额外的菜单权限
* @author CoderYoung
* Date  2022-04-20
*/
@Api(tags = "用户额外的菜单权限")
@RestController
@RequestMapping(value = "/api/system/sysUserMenus")
public class SysUserMenusController extends BizController<ISysUserMenusService,SysUserMenus> {

}