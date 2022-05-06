package com.ad.core.system.service.impl;

import com.ad.core.system.entity.SysRoles;
import com.ad.core.system.mapper.SysRolesMapper;
import com.ad.core.system.service.ISysRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
@Service
public class SysRolesServiceImpl extends ServiceImpl<SysRolesMapper, SysRoles> implements ISysRolesService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
