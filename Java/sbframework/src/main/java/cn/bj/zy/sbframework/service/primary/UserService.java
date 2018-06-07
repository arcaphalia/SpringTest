package cn.bj.zy.sbframework.service.primary;

import cn.bj.zy.sbframework.model.primary.TB_USER;

import java.util.List;

public interface UserService {
    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    List<TB_USER> selectAllUsers()throws Exception;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    TB_USER getByID(TB_USER user)throws Exception;

    /**
     * 添加
     * @param user
     * @throws Exception
     */
    void insert(TB_USER user)throws Exception;
}
