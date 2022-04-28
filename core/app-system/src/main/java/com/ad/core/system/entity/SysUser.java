package com.ad.core.system.entity;

import cn.hutool.json.JSONArray;
import com.ad.common.extension.MyBatisPlusJsonTypeHandler;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author CoderYoung
 * @since 2022-04-27
 */
@TableName(value = "sys_user", autoResultMap = true)
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID-登录ID")
    private String uid;

    @ApiModelProperty("用户名称")
    private String uname;

    @JsonIgnore
    @ApiModelProperty("用户密码")
    private String pwd;

    @ApiModelProperty("公司")
    private String company;

    @ApiModelProperty("部门")
    @TableField(typeHandler = MyBatisPlusJsonTypeHandler.class)
    private JSONArray department;

    @ApiModelProperty("工作职位")
    private String job;

    @ApiModelProperty("工号")
    private Integer sno;

    @ApiModelProperty("身份证号码")
    private String idNum;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("头像")
    private String avatar;

    @JsonIgnore
    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("注册时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @ApiModelProperty("人员状态")
    private Integer status;

    @ApiModelProperty("创建人")
    private String creator;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("修改人")
    private String updater;

    @ApiModelProperty("修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public JSONArray getDepartment() {
        return department;
    }

    public void setDepartment(JSONArray department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysUser{" + "id=" + id + ", uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + ", company=" + company + ", department=" + department + ", job=" + job + ", sno=" + sno + ", idNum=" + idNum + ", email=" + email + ", phone=" + phone + ", avatar=" + avatar + ", sign=" + sign + ", regDate=" + regDate + ", status=" + status + ", creator=" + creator + ", createTime=" + createTime + ", updater=" + updater + ", updateTime=" + updateTime + ", remark=" + remark + "}";
    }
}
