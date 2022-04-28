package com.ad.core.system.controller;

import cn.hutool.core.util.IdUtil;
import com.ad.core.BaseResult;
import com.ad.core.BizController;
import com.ad.core.system.service.ISysOrgService;
import com.ad.core.system.entity.SysOrg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机构
 *
 * @author CoderYoung
 * Date  2022-04-26
 */
@Api(tags = "机构")
@RestController
@RequestMapping(value = "/api/system/sysOrg")
public class SysOrgController extends BizController<ISysOrgService, SysOrg> {

    @Override
    @ApiOperation(value = "新增或修改")
    @PostMapping(path = "saveOrUpdate")
    public BaseResult<Integer> saveOrUpdate(@RequestBody SysOrg entity) {
        if (entity.getId() == null && StringUtils.isBlank(entity.getOrgId())) {
            entity.setOrgId(IdUtil.fastSimpleUUID());
        }
        return super.saveOrUpdate(entity);
    }
}