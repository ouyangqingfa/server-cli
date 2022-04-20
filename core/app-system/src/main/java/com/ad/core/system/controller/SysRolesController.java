package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysRolesService;
import com.ad.core.system.entity.SysRoles;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderYoung
 * Date  2022-04-20
 */
@Api(tags = "系统角色")
@RestController
@RequestMapping(value = "/api/system/sysRoles")
public class SysRolesController extends BizController<ISysRolesService, SysRoles> {

}