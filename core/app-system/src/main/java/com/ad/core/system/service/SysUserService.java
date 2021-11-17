package com.ad.core.system.service;

import com.ad.core.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CoderYoung
 * @since 2021-11-12
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户账号获取信息
     *
     * @param uid
     * @return
     */
    SysUser getUserByUserId(String uid);

}
