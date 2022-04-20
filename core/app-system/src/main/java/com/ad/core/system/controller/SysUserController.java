package com.ad.core.system.controller;

import com.ad.core.BizController;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.entity.SysUser;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 用户表
* @author CoderYoung
* Date  2022-04-20
*/
@Api(tags = "用户表")
@RestController
@RequestMapping(value = "/api/system/sysUser")
public class SysUserController extends BizController<ISysUserService,SysUser> {

}