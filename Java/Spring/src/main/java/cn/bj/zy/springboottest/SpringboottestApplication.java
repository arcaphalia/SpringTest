package cn.bj.zy.springboottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.bj.zy.springboottest.dao")
public class SpringboottestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottestApplication.class, args);
	}
}
