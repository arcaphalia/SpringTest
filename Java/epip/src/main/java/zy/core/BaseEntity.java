package zy.core;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class BaseEntity {

    //主键映射
    @Id
    @Column(name = "id")
    private String id;

    //非实体映射属性
    @Transient
    private Date createTime;

    //非实体映射属性
    @Transient
    private String createUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 生成UUID
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}