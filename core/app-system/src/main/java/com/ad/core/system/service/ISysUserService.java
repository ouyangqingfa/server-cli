package com.ad.core.system.service;

import com.ad.core.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据ID获取用户信息
     *
     * @param uid
     * @return
     */
    SysUser getUserByUserId(String uid);
}
