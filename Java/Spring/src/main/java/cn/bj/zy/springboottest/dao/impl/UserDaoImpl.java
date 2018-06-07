package cn.bj.zy.springboottest.dao.impl;

import cn.bj.zy.springboottest.dao.UserDao;
import cn.bj.zy.springboottest.model.TB_USER;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;
import java.util.UUID;

//@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    static class UserRowMapper implements RowMapper<TB_USER> {
        @Override
        public TB_USER mapRow(ResultSet rs, int rowNum) throws SQLException {
            TB_USER user = new TB_USER();
            user.setUuid(rs.getString("uuid"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setIsRoot(rs.getString("isRoot"));
            user.setRegisterDate(new java.util.Date(rs.getDate("registerDate").getTime()));
            user.setStatus(rs.getInt("status"));
            // if the value is SQL NULL, the value returned is 0
            // 如果希望得到null，可以getObject()判断是否为null
            return user;
        }
    }

    @Override
    public TB_USER findUserByUsername(String username) {
        List<TB_USER> list = jdbcTemplate.query(
                "select * from tb_user where username like ?",
                new Object[]{"%" + username + "%"},
                new int[]{Types.VARCHAR},
                new UserRowMapper()
        );
        if(null!=list&&!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TB_USER> findAllUser() {
        return jdbcTemplate.query("select * from tb_user",new UserRowMapper());
    }

    @Override
    public String saveUser(TB_USER user) {
        final String sql = "insert into tb_user(uuid,userid,username,password,isRoot,registerDate,status) values(?,?,?,?,?,?,?)";
        user.setUuid(UUID.randomUUID().toString().replace("-",""));
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getUuid());
                ps.setString(2, user.getUserId());
                ps.setString(3, user.getUsername());
                ps.setString(4,user.getPassword());
                ps.setString(5,user.getIsRoot());
                ps.setDate(6,new java.sql.Date(user.getRegisterDate().getTime()));
                ps.setInt(7,user.getStatus());
                return ps;
            }
        });
        return user.getUuid();
    }

    @Override
    public void deleteUserByUUID(String uuid) {
        jdbcTemplate.update("delete from tb_user where uuid=?",
                new Object[]{uuid},
                new int[]{Types.VARCHAR});
    }

    @Override
    public void updateUser(TB_USER user) {
        jdbcTemplate.update(
                "update tb_user set password=?,status=? where uuid=?",
                new Object[]{user.getPassword(), user.getStatus(), user.getUuid()});
    }
}