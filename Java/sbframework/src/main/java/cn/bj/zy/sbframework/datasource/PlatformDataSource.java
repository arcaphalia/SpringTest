package cn.bj.zy.sbframework.datasource;

import cn.bj.zy.annotation.SqlReposityPlatform;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 主数据源
 */
@Configuration
public class PlatformDataSource {

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 指定主数据位置
     * @return
     */
    @Bean(name = "platformData")
    @ConfigurationProperties(prefix = "spring.datasource.platform")
    public DataSource platformData() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Platform的SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "platformSqlSessionFactory")
    public SqlSessionFactory platformSqlSessionFactory(@Qualifier("platformData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //扫描位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/platform/*.xml"));
        //类型位置
        bean.setTypeAliasesPackage("cn.bj.zy.sbframework.model.platform");

        // 设置MyBatis分页插件
        PageInterceptor pageInterceptor = this.initPageInterceptor();
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        return bean.getObject();
    }

    /**
     * 主数据源的事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "platformTransactionManager")
    public DataSourceTransactionManager platformTransactionManager(@Qualifier("platformData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "platformmapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("platformSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.bj.zy.sbframework.mapper.platform");
        mapperScannerConfigurer.setAnnotationClass(SqlReposityPlatform.class);
        return mapperScannerConfigurer;
    }

    public PageInterceptor initPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
