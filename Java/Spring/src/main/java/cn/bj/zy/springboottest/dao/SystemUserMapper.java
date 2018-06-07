package cn.bj.zy.springboottest.dao;

import cn.bj.zy.springboottest.model.TB_USER;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SystemUserMapper {

    @Select("select * from tb_user where username=#{username}")
        //@Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
    TB_USER findUserByUsername(String username);

    @Select("select * from tb_user")
    @Results({
            @Result(property = "uuid", column = "uuid", javaType = String.class),
            @Result(property = "userId", column = "userId", javaType = String.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "isRoot", column = "isRoot", javaType = String.class),
            @Result(property = "registerDate", column = "registerDate", javaType = java.util.Date.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
    })
    List<TB_USER> findAllUser();

    @Insert("insert into tb_user(uuid,userId,username,password,isRoot,registerDate,status) " +
            " values(#{uuid}, #{userId},#{username}, #{password},#{isRoot},#{registerDate},#{status})")
    void saveUser(TB_USER user);

    @Delete("delete from tb_user where uuid =#{uuid}")
    void deleteUserByUUID(String uuid);

    @Update("update tb_user set password=#{password},status=#{status} where uuid=#{uuid}")
    void updateUser(TB_USER user);
}
