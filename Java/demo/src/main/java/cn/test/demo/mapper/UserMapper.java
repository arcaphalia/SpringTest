package cn.test.demo.mapper;

import cn.test.demo.config.BaseMapper;
import cn.test.demo.model.TB_USER;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper extends BaseMapper<TB_USER> {

}