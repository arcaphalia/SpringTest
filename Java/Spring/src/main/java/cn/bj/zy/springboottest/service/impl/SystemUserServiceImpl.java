package cn.bj.zy.springboottest.service.impl;

import cn.bj.zy.springboottest.dao.SystemUserMapper;
import cn.bj.zy.springboottest.dao.UserDao;
import cn.bj.zy.springboottest.model.TB_USER;
import cn.bj.zy.springboottest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("systemUserService")
public class SystemUserServiceImpl implements UserService {

    @Resource
    SystemUserMapper systemUserMapper;

    @Transactional(readOnly = true)
    @Override
    public TB_USER findUserByUsername(String username) {
        return systemUserMapper.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TB_USER> findAllUser() {
        return systemUserMapper.findAllUser();
    }

    @Transactional
    @Override
    public String saveUser(TB_USER user) {
        systemUserMapper.saveUser(user);
        return user.getUuid();
    }

    @Transactional
    @Override
    public void deleteUserByUUID(String uuid) {
        systemUserMapper.deleteUserByUUID(uuid);
    }

    @Transactional
    @Override
    public void updateUser(TB_USER user) {
        systemUserMapper.updateUser(user);
    }
}
