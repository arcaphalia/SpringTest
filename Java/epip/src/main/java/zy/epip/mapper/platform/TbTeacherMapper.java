package zy.epip.mapper.platform;


import zy.annotation.SqlReposityPlatform;
import zy.core.BaseMapper;
import zy.epip.model.platform.TbTeacher;

@SqlReposityPlatform
public interface TbTeacherMapper extends BaseMapper<TbTeacher> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_teacher
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_teacher
     *
     * @mbg.generated
     */
    int insert(TbTeacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_teacher
     *
     * @mbg.generated
     */
    TbTeacher selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_teacher
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbTeacher record);
}