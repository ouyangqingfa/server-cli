package com.ad.core.system.service.impl;

import com.ad.core.system.entity.SysUser;
import com.ad.core.system.mapper.SysUserMapper;
import com.ad.core.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public SysUser getUserByUserId(String uid) {
        List<SysUser> sysUsers = baseMapper.selectList(new QueryWrapper<SysUser>().eq("uid", uid));
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }
        return null;
    }

}
