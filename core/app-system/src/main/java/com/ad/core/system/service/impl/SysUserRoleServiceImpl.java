package com.ad.core.system.service.impl;

import com.ad.core.system.entity.SysRoles;
import com.ad.core.system.entity.SysUserRole;
import com.ad.core.system.mapper.SysUserRoleMapper;
import com.ad.core.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色(可能一个用户多个角色) 服务实现类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public boolean delUserRoles(String uid) {
        int del = baseMapper.delete(new QueryWrapper<SysUserRole>().eq("uid", uid));
        return del > 0;
    }

    @Override
    public List<SysRoles> getUserRoles(String uid) {
        return baseMapper.getUserRoles(uid);
    }
}
