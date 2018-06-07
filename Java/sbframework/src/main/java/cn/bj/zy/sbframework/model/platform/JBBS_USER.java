package cn.bj.zy.sbframework.model.platform;

import cn.bj.zy.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "JBBS_USER")
public class JBBS_USER extends BaseEntity{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    private String name;
    private String loginname;
    private String pwd;
    private String img;
    private String email;
    private String birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;

}
