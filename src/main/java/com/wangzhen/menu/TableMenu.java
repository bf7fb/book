package com.wangzhen.menu;

import com.wangzhen.service.BookService;
import com.wangzhen.service.UserService;
import com.wangzhen.service.impl.BookServiceImpl;
import com.wangzhen.service.impl.UserServiceImpl;

import java.lang.reflect.WildcardType;
import java.util.Scanner;

/**
 * @author wz
 * @ClassName TableMenu
 * @date 2023/6/12 23:20
 * @Description TODO
 */
public class TableMenu {
    private UserService userService = new UserServiceImpl();

    private BookService bookService = new BookServiceImpl();

    private Scanner scanner = new Scanner(System.in);
    public void start(){
        int key = 0;
        while (key != 12){
            menu();
            key = scanner.nextInt();
            switch (key){
                case 1:
                    userService.register();
                    continue;
                case 2:
                    userService.login();
                    continue;
                case 3:
                    bookService.queryAllBorrowBook();
                    continue;
                case 4:
                    bookService.queryOneBorrowBook();
                    continue;
                case 5:
                    bookService.queryBookInfo();
                    continue;
                case 6:
                    bookService.queryOneBookInfo();
                    continue;
                case 7:
                    bookService.borrowBook();
                    continue;
                case 8:
                    bookService.returnBook();
                    continue;
                case 9:
                    bookService.updateBook();
                    continue;
                case 10:
                    bookService.deleteBook();
                    continue;
                case 11:
                    bookService.addBook();
                    continue;
                case 12:
                    System.out.println("确定退出？ Y or N");
                    String c = scanner.next();
                    if ("Y".equals(c) || "y".equals(c)){
                        System.out.println("程序结束bye~");
                        break;
                    }
                    System.out.println("退出失败");
                    continue;
            }
        }

    }


    private static void menu(){
        System.out.println("************************欢迎来到图书管理系统************************");
        System.out.println("1.注册用户                                               2.登录账号");
        System.out.println("3.查阅借书情况                                            4.查询个人借书情况");
        System.out.println("5.查阅图书                                               6.查找某本图书");
        System.out.println("7.借阅图书                                               8.归还图书");
        System.out.println("9.修改图书                                               10.删除图书");
        System.out.println("11.添加图书                                              12.退出");
        System.out.println("请输入:");
    }
}
