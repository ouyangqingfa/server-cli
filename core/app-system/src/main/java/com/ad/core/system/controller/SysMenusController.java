package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysMenusService;
import com.ad.core.system.entity.SysMenus;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 
* @author CoderYoung
* Date  2022-04-20
*/
@Api(tags = "系统菜单配置")
@RestController
@RequestMapping(value = "/api/system/sysMenus")
public class SysMenusController extends BizController<ISysMenusService,SysMenus> {

}