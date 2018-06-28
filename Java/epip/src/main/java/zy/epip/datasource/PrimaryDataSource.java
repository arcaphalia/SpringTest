package zy.epip.datasource;

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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import zy.annotation.SqlReposityPrimary;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 主数据源
 */
@Configuration
/*
@MapperScan(
        basePackages = "cn.bj.zy.sbframework.mapper.primary",
        sqlSessionTemplateRef  = "primarySqlSessionTemplate",
        annotationClass = SqlReposityPrimary.class
)
*/
public class PrimaryDataSource {

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 指定主数据位置
     * @return
     */
    @Bean(name = "primaryData")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSource primaryData() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 主数据源的SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //扫描位置
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/primary/*.xml"));
        //类型位置
        bean.setTypeAliasesPackage("zy.epip.model.primary");

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
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /*
    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    */

    @Bean(name = "primarymapperScannerConfigurer")
    @Primary
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("zy.epip.mapper.primary");
        mapperScannerConfigurer.setAnnotationClass(SqlReposityPrimary.class);
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
