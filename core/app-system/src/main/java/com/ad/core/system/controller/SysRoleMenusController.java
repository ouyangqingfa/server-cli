package com.ad.core.system.controller;


import com.ad.core.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单 前端控制器
 * </p>
 *
 * @author CoderYoung
 * @since 2021-11-12
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/api/system/sysRoleMenus")
public class SysRoleMenusController extends BaseController {

}

