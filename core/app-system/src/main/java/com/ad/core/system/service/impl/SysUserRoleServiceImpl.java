package com.ad.core.system.service.impl;

import com.ad.core.system.entity.SysUserRole;
import com.ad.core.system.mapper.SysUserRoleMapper;
import com.ad.core.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色(可能一个用户多个角色) 服务实现类
 * </p>
 *
 * @author CoderYoung
 * @since 2021-11-12
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
