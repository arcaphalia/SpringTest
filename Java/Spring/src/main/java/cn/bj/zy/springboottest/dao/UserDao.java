package cn.bj.zy.springboottest.dao;

import cn.bj.zy.springboottest.model.TB_USER;

import java.util.List;

public interface UserDao {

    TB_USER findUserByUsername(String username);
    List<TB_USER> findAllUser();
    String saveUser(TB_USER user);
    void deleteUserByUUID(String uuid);
    void updateUser(TB_USER user);
}
