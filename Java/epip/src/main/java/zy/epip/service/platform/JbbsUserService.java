package zy.epip.service.platform;

import zy.epip.model.platform.JBBS_USER;

import java.util.List;
import java.util.Map;

public interface JbbsUserService {
    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    List<JBBS_USER> selectAllUsers();

    /**
     * 条件查询
     * @return
     * @throws Exception
     */
    List<JBBS_USER> selectByFilter(Map<String, Object> filter)throws Exception;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    JBBS_USER getByID(JBBS_USER user)throws Exception;

    /**
     * 更新
     * @param user
     * @throws Exception
     */
    void insert(JBBS_USER user)throws Exception;

    /**
     * 更新
     * @param user
     * @throws Exception
     */
    void update(JBBS_USER user)throws Exception;
}
