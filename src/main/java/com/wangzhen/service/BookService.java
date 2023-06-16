package com.wangzhen.service;

/**
 * @author wz
 * @ClassName BookService
 * @date 2023/6/12 16:50
 * @Description TODO
 */
public interface BookService {
    void queryBookInfo();

    void queryOneBookInfo();

    void updateBook();

    void addBook();

    void deleteBook();

    void queryAllBorrowBook();

    void queryOneBorrowBook();

    void borrowBook();

    void returnBook();
}
