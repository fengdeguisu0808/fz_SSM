package com.fanzhou.test;

import com.fanzhou.dao.UserDao;
import com.fanzhou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author fengdeguisu0808
 * @date 2020/7/9 23:04
 */
public class MybatisTest {
    @Test
    public void testSelectAllUser() throws IOException{
        //读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
        users.forEach((user) -> System.out.println(user));
    }

    @Test
    public  void testAddUser() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        String[] address = {"shanghai","nanjing","changsha"};
        for (int i = 0; i < 100; i++) {
            User user = new User(0, "xiaozhou"+i, new Date(), "female", address[i%3]);
            userDao.addUser(user);
        }
        sqlSession.close();
    }

    @Test
    public void exportToExcel()  throws IOException {
        //读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
    }
}
