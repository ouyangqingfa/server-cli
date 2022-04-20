package com.ad.modules.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("td_tag")
@ApiModel(value = "TdTag对象", description = "")
public class TdTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("显示名称")
    private String displayName;

    @ApiModelProperty("查询名称")
    private String queryName;

    @ApiModelProperty("属性描述")
    private String description;

    @ApiModelProperty("上级标签（树形结构）")
    private String parentId;

    @ApiModelProperty("唯一标签：0，非唯一 1、唯一")
    private Integer isOnly;

    @ApiModelProperty("是否开放：0，允许使用时定义  1：不允许使用时定义")
    private Integer isOpen;

    @ApiModelProperty("标签类型：1、标签组 2、标签")
    private Integer tagType;

    @ApiModelProperty("是否可查询：0、可查詢 1、不可查询")
    private Integer isQuery;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("颜色")
    private String color;

    @ApiModelProperty("显示模式：1、仅显示图表 2、仅显示文字 3、同时显示图标和文字")
    private Integer displayModel;

    @ApiModelProperty("标签拥有这：all：公共标签，other（userId）：个人标签")
    private String ownner;

    @ApiModelProperty("创建人")
    private String creater;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    private String isdel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getIsOnly() {
        return isOnly;
    }

    public void setIsOnly(Integer isOnly) {
        this.isOnly = isOnly;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }

    public Integer getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDisplayModel() {
        return displayModel;
    }

    public void setDisplayModel(Integer displayModel) {
        this.displayModel = displayModel;
    }

    public String getOwnner() {
        return ownner;
    }

    public void setOwnner(String ownner) {
        this.ownner = ownner;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "TdTag{" +
        "id=" + id +
        ", name=" + name +
        ", displayName=" + displayName +
        ", queryName=" + queryName +
        ", description=" + description +
        ", parentId=" + parentId +
        ", isOnly=" + isOnly +
        ", isOpen=" + isOpen +
        ", tagType=" + tagType +
        ", isQuery=" + isQuery +
        ", icon=" + icon +
        ", color=" + color +
        ", displayModel=" + displayModel +
        ", ownner=" + ownner +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", updater=" + updater +
        ", updateTime=" + updateTime +
        ", isdel=" + isdel +
        "}";
    }
}
