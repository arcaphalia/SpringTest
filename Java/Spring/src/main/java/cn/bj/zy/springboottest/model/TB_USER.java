package cn.bj.zy.springboottest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TB_USER {

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private String uuid;     // 表主键

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private String username; // 登录用户名
    private String password; // 登录密码
    private String isRoot; // 最高权限管理员; "1":root "0": notRoot
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    private Integer status; // 账号状态： 0：正常；1：禁用

}
