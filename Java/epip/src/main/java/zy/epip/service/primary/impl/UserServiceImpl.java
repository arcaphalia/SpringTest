package zy.epip.service.primary.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.epip.mapper.primary.UserMapper;
import zy.epip.model.primary.TB_USER;
import zy.epip.service.primary.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TB_USER> selectAllUsers()throws Exception{
        return userMapper.selectAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public TB_USER getByID(TB_USER user){
        return userMapper.selectOne(user);
    }

    /**
     * 添加
     * @param user
     * @throws Exception
     */
    @Override
    public void insert(TB_USER user)throws Exception{
        userMapper.insert(user);
    }
}
