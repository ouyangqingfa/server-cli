package com.ad.core.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-20
 */
@TableName("sys_menus")
@ApiModel(value = "SysMenus对象", description = "")
public class SysMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单ID")
    private String menuId;

    @ApiModelProperty("父菜单ID")
    private String pid;

    @ApiModelProperty("菜单标识")
    private String key;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("组件路径-前端一致")
    private String component;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态控制")
    private Integer status;

    @ApiModelProperty("备注")
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SysMenus{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", pid=" + pid +
                ", key=" + key +
                ", title=" + title +
                ", icon=" + icon +
                ", component=" + component +
                ", sort=" + sort +
                ", status=" + status +
                ", remarks=" + remarks +
                "}";
    }
}
