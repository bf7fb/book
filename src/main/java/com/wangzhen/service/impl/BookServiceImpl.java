package com.wangzhen.service.impl;

import com.wangzhen.dto.BookDto;
import com.wangzhen.mapper.BookMapper;
import com.wangzhen.pojo.Book;
import com.wangzhen.service.BookService;
import com.wangzhen.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;

import static com.wangzhen.utils.UserInfo.LOGIN_USER;

/**
 * @author wz
 * @ClassName BookServiceImpl
 * @date 2023/6/12 16:51
 * @Description TODO
 */
public class BookServiceImpl implements BookService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    private Scanner scanner = new Scanner(System.in);

    private static final int paceNum = 20;

    @Override
    public void queryBookInfo() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("**************************图书信息详情******************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.queryBookInfo();
        // 展示书籍
        for (int i = 0; i < 4; i++) {
            if (i == 0){
                System.out.print("序号：");
            }else if (i == 1){
                System.out.print("书名：");
            }else if (i == 2){
                System.out.print("作者：");
            }else if (i == 3){
                System.out.print("借阅：");
            }
            for (int j = 0; j < paceNum; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (Book book : books) {
            System.out.print(book.getBookId());
            for (int i = 0; i < paceNum; i++) {
                System.out.print(" ");
            }
            System.out.print(book.getBookName());
            for (int i = 0; i < paceNum - book.getBookName().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(book.getAuthor());
            for (int i = 0; i < paceNum - book.getAuthor().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.println(book.getNameId() == 0 ? "否" : "是");
        }
        sqlSession.close();
    }

    @Override
    public void queryOneBookInfo() {
        if (!checkLogin()) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        System.out.println("请输入图书名称/图书作者");
        String key = scanner.next();
        List<Book> books = mapper.queryOneBookInfo("%" + key + "%");
        // 展示数据
//        System.out.println(books);
        // 展示书籍
        for (int i = 0; i < 4; i++) {
            if (i == 0){
                System.out.print("序号：");
            }else if (i == 1){
                System.out.print("书名：");
            }else if (i == 2){
                System.out.print("作者：");
            }else if (i == 3){
                System.out.print("借阅：");
            }
            for (int j = 0; j < paceNum; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (Book book : books) {
            System.out.print(book.getBookId());
            for (int i = 0; i < paceNum; i++) {
                System.out.print(" ");
            }
            System.out.print(book.getBookName());
            for (int i = 0; i < paceNum - book.getBookName().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(book.getAuthor());
            for (int i = 0; i < paceNum - book.getAuthor().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.println(book.getNameId() == 0 ? "否" : "是");
        }
        sqlSession.close();
    }

    /**
     * 修改图书
     */
    @Override
    public void updateBook() {
        if (!checkLogin()) {
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        queryBookInfo();
        System.out.println("请输入要修改图书的id");
        int id = scanner.nextInt();
        Book book = mapper.queryBookById(id);
        if (book == null) {
            System.out.println("要修改的图书不存在，请重新操作~");
            return;
        }
        System.out.println("请输入图书名称");
        String bookName = scanner.next();
        System.out.println("请输入图书作者");
        String author = scanner.next();
        Book updateBook = new Book();
        updateBook.setBookId(id);
        updateBook.setBookName(bookName);
        updateBook.setAuthor(author);
        mapper.updateBook(updateBook);
        sqlSession.commit();
        System.out.println("修改成功~");
        sqlSession.close();


    }

    @Override
    public void addBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************添加图书****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        System.out.println("请输入图书名称：");
        String bookName = scanner.next();
        System.out.println("请输入图书作者：");
        String author = scanner.next();
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        mapper.addBook(book);
        sqlSession.commit();
        System.out.println("添加成功~");
        sqlSession.close();
    }

    @Override
    public void deleteBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************删除图书****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        queryBookInfo();
        System.out.println("请输入要删除的图书Id");
        int id = scanner.nextInt();
        Book book = mapper.queryBookById(id);
        if (book == null) {
            System.out.println("输入的图书Id不存在，删除失败~");
            return;
        }
        mapper.deleteBook(id);
        sqlSession.commit();
        System.out.println("删除成功");
        sqlSession.close();
    }

    /**
     * 查询所有人借书情况
     */
    @Override
    public void queryAllBorrowBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************借阅图书一览****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<BookDto> bookDtos = mapper.queryAllBorrowBook();
//        System.out.println(bookDtos);
        // 展示数据
        for (int i = 0; i < 4; i++) {
            if (i == 0){
                System.out.print("书籍Id：");
            }else if (i == 1){
                System.out.print("书籍名称：");
            }else if (i == 2){
                System.out.print("借阅书籍：");
            }else if (i == 3){
                System.out.print("书籍作者：");
            }
            for (int j = 0; j < paceNum; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (BookDto bookDto : bookDtos) {
            System.out.print(bookDto.getBookId());
            for (int i = 0; i < paceNum + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getName());
            for (int i = 0; i < paceNum - bookDto.getName().length() + 5; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getBookName());
            for (int i = 0; i < paceNum - bookDto.getBookName().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getAuthor());
            System.out.println();
        }
        sqlSession.close();
    }

    /**
     * 查询某人借书情况
     */
    @Override
    public void queryOneBorrowBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************某人借阅图书一览****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        System.out.println("请输入要查询的人名：");
        String name = scanner.next();
        List<BookDto> bookDtos = mapper.querryOneBorrowBook("%" + name + "%");
//        System.out.println(bookDtos);
        // 展示数据
        for (int i = 0; i < 4; i++) {
            if (i == 0){
                System.out.print("书籍Id：");
            }else if (i == 1){
                System.out.print("书籍名称：");
            }else if (i == 2){
                System.out.print("借阅书籍：");
            }else if (i == 3){
                System.out.print("书籍作者：");
            }
            for (int j = 0; j < paceNum; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (BookDto bookDto : bookDtos) {
            System.out.print(bookDto.getBookId());
            for (int i = 0; i < paceNum + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getName());
            for (int i = 0; i < paceNum - bookDto.getName().length() + 5; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getBookName());
            for (int i = 0; i < paceNum - bookDto.getBookName().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getAuthor());
            System.out.println();
        }
        sqlSession.close();
    }

    /**
     * 借书
     */
    @Override
    public void borrowBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************借阅图书****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        queryBookInfo();
        System.out.println("请输入要借阅的图书Id：");
        int bookId = scanner.nextInt();
        Book book = mapper.queryBookIfBorrow(bookId);
        if (book.getNameId() != 0){
            // 表示书已被借出
            System.out.println("该本书已被其他同学借出，无法借阅！");
            return;
        }
        int userId = LOGIN_USER.getUserId();
        mapper.borrowBook(bookId,userId);
        sqlSession.commit();
        System.out.println("借阅成功~");
        sqlSession.close();
    }

    /**
     * 还书
     */
    @Override
    public void returnBook() {
        if (!checkLogin()) {
            return;
        }
        System.out.println("************************归还图书****************************");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        int userId = LOGIN_USER.getUserId();
        List<BookDto> bookDtos = mapper.queryUserBorrowBooks(userId);
        // 展示这个人借的图书
        if (bookDtos == null) {
            System.out.println("您还并未借书，不能归还图书哦~");
            return;
        }
        // 展示数据
        for (int i = 0; i < 4; i++) {
            if (i == 0){
                System.out.print("书籍Id：");
            }else if (i == 1){
                System.out.print("书籍名称：");
            }else if (i == 2){
                System.out.print("借阅书籍：");
            }else if (i == 3){
                System.out.print("书籍作者：");
            }
            for (int j = 0; j < paceNum; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (BookDto bookDto : bookDtos) {
            System.out.print(bookDto.getBookId());
            for (int i = 0; i < paceNum + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getName());
            for (int i = 0; i < paceNum - bookDto.getName().length() + 5; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getBookName());
            for (int i = 0; i < paceNum - bookDto.getBookName().length() + 6; i++) {
                System.out.print(" ");
            }
            System.out.print(bookDto.getAuthor());
            System.out.println();
        }
        // 判断要归还的书是否被该用户借
        System.out.println("请输入要归还的图书Id：");
        int bookId = scanner.nextInt();
        Book book = mapper.queryIfUserBorrowThisBook(userId, bookId);
        if (book == null) {
            System.out.println("您并未借这本书，不能归还这本书~");
            return;
        }
        // 归还
        mapper.returnBook(bookId);
        sqlSession.commit();
        System.out.println("归还成功~");
        sqlSession.close();
    }

    private boolean checkLogin(){
        if (LOGIN_USER == null){
            System.out.println("您尚未登录~");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        BookServiceImpl bookService = new BookServiceImpl();
//        bookService.queryBookInfo();
//        bookService.queryOneBookInfo();
//        bookService.updateBook();
//        bookService.addBook();
//        bookService.deleteBook();
//        bookService.queryAllBorrowBook();
//        bookService.queryOneBorrowBook();
//        bookService.borrowBook();
    }
}
