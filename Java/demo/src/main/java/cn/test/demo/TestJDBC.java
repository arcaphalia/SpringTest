package cn.test.demo;


import cn.test.demo.mapper.UserMapper;
import cn.test.demo.model.TB_USER;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestJDBC {

    Logger logger = LoggerFactory.getLogger(getClass());
    static int i = 2;

    @Resource
    UserMapper userMapper;

    @Test
    public void saveUser() {
        TB_USER user = new TB_USER();
        user.setUuid(UUID.randomUUID().toString().replace("-", ""));
        user.setStatus(0);
        user.setRegisterDate(new Date());
        user.setPassword("123456");
        user.setIsRoot("0");
        user.setUsername("张展" + i);
        user.setUserId("testZhang" + i);
        userMapper.insert(user);
        String uuid = user.getUuid();
        System.out.println(uuid);
    }

    @Test
    public void findUser1() {

    }


    @Test
    public void updateUser() {

    }

    @Test
    public void deleteUser() {

    }
}