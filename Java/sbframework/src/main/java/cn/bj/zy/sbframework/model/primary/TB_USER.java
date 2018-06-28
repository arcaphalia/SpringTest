package cn.bj.zy.sbframework.model.primary;

import cn.bj.zy.annotation.ExcelField;
import cn.bj.zy.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "TB_USER")
public class TB_USER extends BaseEntity{

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**用户ID**/
    @Column(name = "userId")
    @ExcelField(title = "用户ID")
    private String userId;

    /**用户名**/
    @Column(name = "username")
    @ExcelField(title = "姓名")
    private String username; // 登录用户名

    /**密码**/
    @Column(name = "password")
    @ExcelField(title = "密码")
    private String password; // 登录密码

    @Column(name = "isRoot")
    private String isRoot; // 最高权限管理员; "1":root "0": notRoot

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "registerDate")
    @ExcelField(title = "注册时间")
    private Date registerDate;

    @Column(name = "status")
    private Integer status; // 账号状态： 0：正常；1：禁用

}
