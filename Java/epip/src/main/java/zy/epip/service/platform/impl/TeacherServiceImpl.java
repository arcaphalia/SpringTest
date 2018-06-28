package zy.epip.service.platform.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.epip.mapper.platform.TbTeacherMapper;
import zy.epip.model.platform.TbTeacher;
import zy.epip.service.platform.TeacherService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TbTeacherMapper tbTeacherMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TbTeacher> selectAll(){
        return tbTeacherMapper.selectAll();
    }

    /**
     * 条件查询
     * @return
     * @throws Exception
     */
    @Override
    public List<TbTeacher> selectByFilter(Map<String, Object> filter)throws Exception{
        //return jbbsUserMapper.selectByFilter(filter);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public TbTeacher getByID(TbTeacher entity){
        //return jbbsUserMapper.selectOne(user);
        return null;
    }

    /**
     * 添加
     * @param entity
     * @throws Exception
     */
    @Override
    public void insert(TbTeacher entity)throws Exception{
        //jbbsUserMapper.insert(user);
    }

    /**
     * 更新
     * @param entity
     * @throws Exception
     */
    @Override
    public void update(TbTeacher entity)throws Exception{
        //jbbsUserMapper.updateByPrimaryKey(user);
    }
}
