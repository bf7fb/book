package com.wangzhen.service.impl;

import com.wangzhen.mapper.UserMapper;
import com.wangzhen.pojo.User;
import com.wangzhen.service.UserService;
import com.wangzhen.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.Scanner;

import static com.wangzhen.utils.UserInfo.LOGIN_USER;

/**
 * @author wz
 * @ClassName UserServiceImpl
 * @date 2023/6/12 17:25
 * @Description TODO
 */
public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    private Scanner scanner = new Scanner(System.in);
    /**
     * 注册账号
     */
    @Override
    public void register() {
        System.out.println("*****************************注册界面********************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("欢迎来到注册页面");
        System.out.println("请输入账号");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        System.out.println("请输入您的姓名");
        String name = scanner.nextLine();
        User queryUser = userMapper.queryUsername(username);
        if (queryUser != null) {
            System.out.println("账号已存在，请重新输入");
            return ;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        userMapper.register(user);
        System.out.println("注册成功");
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 登录
     */
    @Override
    public void login() {
        System.out.println("*****************************登录界面********************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("请输入账号：");
        String userName = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        User loginUser = new User();
        loginUser.setUsername(userName);
        loginUser.setPassword(password);
        User user = mapper.login(loginUser);
        System.out.println(user == null ? "登录失败~" : "登陆成功");
        LOGIN_USER = user;
    }




    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.register();
        userService.login();
    }
}
