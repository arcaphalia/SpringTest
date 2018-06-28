package zy.epip.service.platform;

import zy.epip.model.platform.TbTeacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    List<TbTeacher> selectAll();

    /**
     * 条件查询
     * @return
     * @throws Exception
     */
    List<TbTeacher> selectByFilter(Map<String, Object> filter)throws Exception;

    /**
     *
     * @param entity
     * @return
     * @throws Exception
     */
    TbTeacher getByID(TbTeacher entity)throws Exception;

    /**
     * 更新
     * @param entity
     * @throws Exception
     */
    void insert(TbTeacher entity)throws Exception;

    /**
     * 更新
     * @param entity
     * @throws Exception
     */
    void update(TbTeacher entity)throws Exception;
}
