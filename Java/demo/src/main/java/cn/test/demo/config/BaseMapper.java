package cn.test.demo.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Nick
 * @version V1.0.0
 * @Date 2017/12/1 13:45
 * @description BaseMapper
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
