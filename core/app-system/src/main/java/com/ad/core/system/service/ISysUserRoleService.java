package com.ad.core.system.service;

import com.ad.core.system.entity.SysRoles;
import com.ad.core.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色(可能一个用户多个角色) 服务类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 删除用户所有角色
     *
     * @param uid
     * @return
     */
    boolean delUserRoles(String uid);

    /**
     * 获取用户的所有角色
     *
     * @param uid
     * @return
     */
    List<SysRoles> getUserRoles(String uid);

}
