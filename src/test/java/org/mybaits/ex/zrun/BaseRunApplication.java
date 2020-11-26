/**
 * FileName：BaseRunApplication
 * Version：
 * Date：2020/11/26
 * Copyright 马丁洛克 Corporation 2020 版权所有
 */
package org.mybaits.ex.zrun;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.mybaits.ex.id.IdWorker;

import java.io.InputStream;

/**
 * @className BaseRunApplication
 * @describe 测试超类
 * @author panxw
 * @date 2020/11/26 10:26
 */

public class BaseRunApplication {

    private SqlSessionFactory sessionFactory;

    private SqlSession sqlSession;

    private String resource = "mybatis-config.xml";

    private IdWorker idWorker;

    @Before
    public void init() {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sessionFactory.openSession(true);

            idWorker = new IdWorker();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getMapper(Class<T> clazz) {
        T mapper = sqlSession.getMapper(clazz);
        return mapper;
    }

    /**
     * nextId 生成一个Id 
     * @author panxw 
     * @return java.lang.Long
     */
    public Long nextId() {
        return idWorker.nextId();
    }

    public String nextStringId() {
        return idWorker.nextStringId();
    }
}
