package cn.bj.zy.sbframework.mapper.primary;

import cn.bj.zy.annotation.SqlReposityPrimary;
import cn.bj.zy.core.BaseMapper;
import cn.bj.zy.sbframework.model.primary.TB_USER;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@SqlReposityPrimary
public interface UserMapper extends BaseMapper<TB_USER> {

    @Select("select * from tb_user")
    List<TB_USER> selectAllUsers()throws Exception;
}
