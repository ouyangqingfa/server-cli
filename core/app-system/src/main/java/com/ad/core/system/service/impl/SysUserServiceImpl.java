package com.ad.core.system.service.impl;

import com.ad.core.system.entity.SysUser;
import com.ad.core.system.entity.SysUserRole;
import com.ad.core.system.mapper.SysUserMapper;
import com.ad.core.system.service.ISysUserRoleService;
import com.ad.core.system.service.ISysUserService;
import com.ad.core.system.vo.UserParamVo;
import com.ad.core.system.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bestvike.linq.Linq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private ISysUserRoleService roleService;

    @Autowired
    public SysUserServiceImpl(ISysUserRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public SysUser getUserByUserId(String uid) {
        List<SysUser> sysUsers = baseMapper.selectList(new QueryWrapper<SysUser>().eq("uid", uid));
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }
        return null;
    }

    @Override
    public IPage<UserVo> getUserPage(int current, int pageSize, UserParamVo params) {
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        if (params != null) {
            if (StringUtils.isNotBlank(params.getName())) {
                userQueryWrapper.like("uname", params.getName());
            }
            if (StringUtils.isNotBlank(params.getOrgId())) {
                userQueryWrapper.apply("JSON_CONTAINS(department,JSON_ARRAY({0}))", params.getOrgId());
            }
            if (StringUtils.isNotBlank(params.getName())) {
                userQueryWrapper.and(q -> q.like("uid", params.getName()).or().like("uname", params.getName()));
            }
        }

        Page<SysUser> dbPage;
        if (params != null && StringUtils.isNotBlank(params.getRoleId())) {
            dbPage = baseMapper.getUserByRole(new Page<>(current, pageSize), params.getRoleId(), userQueryWrapper);
        } else {
            dbPage = baseMapper.selectPage(new Page<>(current, pageSize), userQueryWrapper);
        }

        IPage<UserVo> userPage = new Page<>(dbPage.getCurrent(), dbPage.getSize(), dbPage.getTotal());
        List<UserVo> userVos = dbPage.getRecords().stream().map(UserVo::new).collect(Collectors.toList());
        if (userVos.size() > 0) {
            List<SysUserRole> userRoles = roleService.list(new QueryWrapper<SysUserRole>().in("uid", dbPage.getRecords().stream().map(SysUser::getUid).collect(Collectors.toList())));
            Linq.of(userRoles).groupBy(SysUserRole::getUid).forEach(g -> {
                for (UserVo userVo : userVos) {
                    if (userVo.getUid().equalsIgnoreCase(g.getKey())) {
                        userVo.setRoles(g.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
                        break;
                    }
                }
            });
        }

        userPage.setRecords(userVos);
        return userPage;
    }


}
