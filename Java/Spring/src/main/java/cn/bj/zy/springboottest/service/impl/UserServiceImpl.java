package cn.bj.zy.springboottest.service.impl;

import cn.bj.zy.springboottest.dao.UserDao;
import cn.bj.zy.springboottest.model.TB_USER;
import cn.bj.zy.springboottest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public TB_USER findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TB_USER> findAllUser() {
        return userDao.findAllUser();
    }

    @Transactional
    @Override
    public String saveUser(TB_USER user) {
        return userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUserByUUID(String uuid) {
        userDao.deleteUserByUUID(uuid);
    }

    @Transactional
    @Override
    public void updateUser(TB_USER user) {
        userDao.updateUser(user);
    }
}
