package com.bat.springcloud.datasource.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import com.bat.springcloud.datasource.mybatis.dao.CustomStructureDao;
import com.bat.springcloud.datasource.mybatis.util.BuildDatabaseTableUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Mybatis 应用
 *
 * @author ZhengYu
 * @version 1.0 2020/7/9 23:51
 **/
public class MybatisNativeApplication {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 建库建表
        initProjectConfig();

        // 原生方式调用mybatis
        SqlSession sqlSession = sqlSessionFactory.openSession();
        nativeInvokeSqlSession(sqlSession);
    }

    /**
     * 建库建表
     *
     * @author ZhengYu
     */
    private static void initProjectConfig() {
        try {
            new BuildDatabaseTableUtil(
                    Resources.getResourceAsReader("mybatis-config.xml"),
                    Resources.getResourceAsReader("sql/script.sql")
            ).runSqlScript();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * mybatis 原生方式调用
     *
     * @param sqlSession mybatis暴露API
     * @author ZhengYu
     */
    private static void nativeInvokeSqlSession(SqlSession sqlSession) {
        if (sqlSession == null) {
            return;
        }

        // 方式1 通过 SqlSession 原生 API调用
        CustomStructurePO customStructureOfSqlSessionApi = sqlSession.selectOne("com.bat.springcloud.datasource.mybatis.dao.CustomStructureDao.queryCustomStructureById", 1L);
        System.out.println("SqlSession API 方式调用结果为: " + JSONObject.toJSONString(customStructureOfSqlSessionApi));

        // 方式2 通过 SqlSession 获取 Mapper代理对象方式调用
        CustomStructureDao customStructureDao1 = sqlSession.getMapper(CustomStructureDao.class);
        CustomStructurePO customStructureOfProxyMapper = customStructureDao1.queryCustomStructureById(1L);
        System.out.println("SqlSession 获取 Mapper代理对象方式调用结果为: " + JSONObject.toJSONString(customStructureOfProxyMapper));

        // 方式3 使用JDK代理生成代理对象, 为方式2的简单实现
        CustomStructureDao customStructureDao2 = (CustomStructureDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{CustomStructureDao.class},
                (proxy, method, args) -> {
                    String statement = CustomStructureDao.class.getName() + "." + method.getName();
                    return sqlSession.selectOne(statement, args[0]);
                });
        CustomStructurePO customStructureOfJDKProxyMapper = customStructureDao2.queryCustomStructureById(1L);
        System.out.println("使用JDK代理生成代理对象方式调用结果为: " + JSONObject.toJSONString(customStructureOfJDKProxyMapper));
    }
}
