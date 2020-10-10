package com.bat.springcloud.datasource.mybatis.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.sql.Connection;
import java.util.Arrays;

/**
 * 建库建表工具类
 *
 * @author ZhengYu
 * @version 1.0 2020/7/9 23:56
 **/
@Slf4j
public class BuildDatabaseTableUtil {

    private Reader mybatisConfigReader;

    private Reader[] scriptReaders;

    private SqlSessionFactory sqlSessionFactory;

    public BuildDatabaseTableUtil(Reader mybatisConfigReader, Reader... scriptReaders) {
        this.mybatisConfigReader = mybatisConfigReader;
        this.scriptReaders = scriptReaders;
    }

    /**
     * 执行sql脚本
     *
     * @author ZhengYu
     */
    public void runSqlScript() {
        try {
            Connection connection = getConnection();

            ScriptRunner runner = new ScriptRunner(connection);
            Arrays.stream(scriptReaders).forEach(runner::runScript);
        } catch (Exception e) {
            log.error("获取连接失败, 原因:[{}] [{}]", e.getMessage(), e);
        }

    }

    private Connection getConnection() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfigReader);
        }
        final SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession.getConnection();
    }
}
