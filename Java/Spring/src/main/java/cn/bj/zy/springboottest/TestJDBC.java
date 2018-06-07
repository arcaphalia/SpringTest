package cn.bj.zy.springboottest;

import cn.bj.zy.springboottest.model.TB_USER;
import cn.bj.zy.springboottest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestJDBC {

    Logger logger = LoggerFactory.getLogger(getClass());
    int i = 0;

    //@Resource
    //UserService userService;

    @Resource
    UserService systemUserService;

    @Test
    public void saveUser() {
        TB_USER user = new TB_USER();
        user.setStatus(0);
        user.setRegisterDate(new Date());
        user.setPassword("123456");
        user.setIsRoot("0");
        user.setUsername("张展" + i);
        user.setUserId("testZhang" + i);
        ++i;
        //String uuid = userService.saveUser(user);
        String uuid = systemUserService.saveUser(user);
        logger.info("增加成功：uuid：{}", uuid);
    }

    @Test
    public void findUser1() {
        //TB_USER user = userService.findUserByUsername("张展");
        TB_USER user = systemUserService.findUserByUsername("张展");
        logger.info("user:{}", user);
    }

    @Test
    public void findUser2() {
        //List<TB_USER> list = userService.findAllUser();
        List<TB_USER> list = systemUserService.findAllUser();
        logger.info("userlist:{}", list);
    }

    @Test
    public void updateUser() {
        //TB_USER user = userService.findUserByUsername("张展");
        TB_USER user = systemUserService.findUserByUsername("张展");
        if(user != null) {
            user.setPassword("1234567");
            user.setStatus(1);
            //userService.updateUser(user);
            systemUserService.updateUser(user);
        }
    }

    @Test
    public void deleteUser() {
        //userService.deleteUserByUUID("cbb0759e58114171bb33437c7db2a276");
        systemUserService.deleteUserByUUID("cbb0759e58114171bb33437c7db2a276");
    }
}