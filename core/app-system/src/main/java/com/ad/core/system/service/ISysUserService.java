package com.ad.core.system.service;

import com.ad.core.system.entity.SysUser;
import com.ad.core.system.vo.UserParamVo;
import com.ad.core.system.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-27
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据ID获取用户信息
     *
     * @param uid
     * @return
     */
    SysUser getUserByUserId(String uid);

    /**
     * 分页获取用户列表
     *
     * @param current
     * @param pageSize
     * @param params
     * @return
     */
    IPage<UserVo> getUserPage(int current, int pageSize, UserParamVo params);

}
