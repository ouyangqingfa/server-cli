package com.ad.core.system.mapper;

import com.ad.core.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据uid获取用户对象
     *
     * @param uid
     * @return
     */
    @Select("select * from sys_user where uid='${uid}'")
    SysUser getUserByUserId(String uid);

    /**
     * 根据角色分页获取用户列表
     *
     * @param page
     * @param roleId
     * @param queryWrapper
     * @return
     */
    @Select("select * from (select t1.* from sys_user t1 where exists(select t2.uid from sys_user_role t2 where t2.uid=t1.uid and t2.role_id='${roleId}')) AS q ${qw.customSqlSegment}")
    Page<SysUser> getUserByRole(@Param("page") Page<SysUser> page, @Param("roleId") String roleId, @Param("qw") Wrapper<SysUser> queryWrapper);

}
