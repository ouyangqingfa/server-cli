package com.ad.core.system.service;

import com.ad.core.system.entity.SysOrg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 机构 服务类
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-26
 */
public interface ISysOrgService extends IService<SysOrg> {

    /**
     * 获取所有子项ID
     *
     * @param orgId
     * @return
     */
    List<String> getAllChildIds(String orgId);
}
