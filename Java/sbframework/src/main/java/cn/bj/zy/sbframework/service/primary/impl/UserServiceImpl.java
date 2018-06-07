package cn.bj.zy.sbframework.service.primary.impl;

import cn.bj.zy.sbframework.mapper.primary.UserMapper;
import cn.bj.zy.sbframework.model.primary.TB_USER;
import cn.bj.zy.sbframework.service.primary.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
