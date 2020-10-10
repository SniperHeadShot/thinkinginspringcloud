package com.bat.springcloud.datasource.mybatis.config;

import com.bat.springcloud.datasource.mybatis.dao.CustomStructureDao;
import com.bat.springcloud.datasource.mybatis.service.CustomStructureService;
import com.bat.springcloud.datasource.mybatis.service.impl.CustomStructureServiceImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Mybatis 整合 Spring 配置类
 *
 * @author ZhengYu
 * @version 1.0 2020/8/19 17:12
 **/
@MapperScan("com.bat.springcloud.datasource.mybatis.dao")
public class MybatisSpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.9.241:3306/data_center?useSSL=false&amp;characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("nYd*4M]ipIv+");
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResource("classpath:mapper/CustomStructureMapper.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public CustomStructureService customStructureService(CustomStructureDao customStructureDao, PlatformTransactionManager transactionManager) {
        return new CustomStructureServiceImpl(customStructureDao, transactionManager);
    }
}
