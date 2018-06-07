package cn.bj.zy.sbframework.service.platform.impl;

import cn.bj.zy.sbframework.mapper.platform.JbbsUserMapper;
import cn.bj.zy.sbframework.model.platform.JBBS_USER;
import cn.bj.zy.sbframework.service.platform.JbbsUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("jbbsUserService")
public class JbbsUserServiceImpl implements JbbsUserService {

    @Resource
    JbbsUserMapper jbbsUserMapper;

    @Transactional(readOnly = true)
    @Override
    public List<JBBS_USER> selectAllUsers(){
        return jbbsUserMapper.selectAll();
    }

    /**
     * 条件查询
     * @return
     * @throws Exception
     */
    @Override
    public List<JBBS_USER> selectByFilter(Map<String, Object> filter)throws Exception{
        //return jbbsUserMapper.selectByFilter(filter);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public JBBS_USER getByID(JBBS_USER user){
        //return jbbsUserMapper.selectOne(user);
        return null;
    }

    /**
     * 添加
     * @param user
     * @throws Exception
     */
    @Override
    public void insert(JBBS_USER user)throws Exception{
        //jbbsUserMapper.insert(user);
    }

    /**
     * 更新
     * @param user
     * @throws Exception
     */
    @Override
    public void update(JBBS_USER user)throws Exception{
        //jbbsUserMapper.updateByPrimaryKey(user);
    }
}
