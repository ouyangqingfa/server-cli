package com.ad.core.system.vo;

/**
 * 用户查询参数
 *
 * @author CoderYoung
 */
public class UserParamVo {

    private String roleId;
    private String orgId;
    private String name;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
