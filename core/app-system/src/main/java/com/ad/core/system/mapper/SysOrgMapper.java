package com.ad.core.system.mapper;

import com.ad.core.system.entity.SysOrg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 机构 Mapper 接口
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-26
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    /**
     * 获取包含自己的所有子项机构ID
     *
     * @param orgId
     * @return
     */
    @Select("SELECT t1.org_id\n" +
            "FROM (SELECT * FROM sys_org WHERE sys_org.pid IS NOT NULL) t1,\n" +
            "     (SELECT @pid := '${orgId}') pd\n" +
            "WHERE FIND_IN_SET(pid, @pid) > 0 AND @pid := concat(@pid, ',', org_id)\n" +
            "union\n" +
            "select org_id\n" +
            "from sys_org\n" +
            "where org_id = '${orgId}';")
    List<String> getAllChildIds(String orgId);

}
