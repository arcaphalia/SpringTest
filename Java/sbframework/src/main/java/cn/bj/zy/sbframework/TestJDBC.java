package cn.bj.zy.sbframework;

import cn.bj.zy.sbframework.model.primary.TB_USER;
import cn.bj.zy.sbframework.service.primary.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestJDBC {

    Logger logger = LoggerFactory.getLogger(getClass());
    int i = 0;

    //@Resource
    //UserService userService;

    @Resource
    UserService userService;

    @Test
    public void findUser1() {
        Page<TB_USER> page = PageHelper.startPage(1, 10).doSelectPage(() -> {
            try {
                userService.selectAllUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void findUser2() {
        try {
            TB_USER user = new TB_USER();
            user.setUserId("testZhang0");
            user =  userService.getByID(user);
            System.out.println(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}