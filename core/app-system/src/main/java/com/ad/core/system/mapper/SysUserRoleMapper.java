package com.ad.core.system.mapper;

import com.ad.core.system.entity.SysRoles;
import com.ad.core.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色(可能一个用户多个角色) Mapper 接口
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取用户的所有角色
     *
     * @param uid
     * @return
     */
    @Select("select t2.* from sys_user_role t1 left join sys_user_role t2 on t1.role_id=t2.role_id where t1.uid =${uid}")
    List<SysRoles> getUserRoles(String uid);

}
