package zy.epip.mapper.primary;

import org.apache.ibatis.annotations.Select;
import zy.annotation.SqlReposityPrimary;
import zy.core.BaseMapper;
import zy.epip.model.primary.TB_USER;

import java.util.List;

@SqlReposityPrimary
public interface UserMapper extends BaseMapper<TB_USER> {

    @Select("select * from tb_user")
    List<TB_USER> selectAllUsers()throws Exception;
}
