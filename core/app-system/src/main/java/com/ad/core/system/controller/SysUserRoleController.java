package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysUserRoleService;
import com.ad.core.system.entity.SysUserRole;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色(可能一个用户多个角色)
 *
 * @author CoderYoung
 * Date  2022-04-20
 */
@Api(tags = "用户角色", description = "可能一个用户多个角色")
@RestController
@RequestMapping(value = "/api/system/sysUserRole")
public class SysUserRoleController extends BizController<ISysUserRoleService, SysUserRole> {

}