package com.wangzhen.mapper;

import com.wangzhen.dto.BookDto;
import com.wangzhen.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wz
 * @ClassName BookMapper
 * @date 2023/6/12 16:52
 * @Description TODO
 */
public interface BookMapper {
    @Select("select * from tb_book")
    List<Book> queryBookInfo();

    @Select("select * from tb_book where bookname like #{key} or author like #{key}")
    List<Book> queryOneBookInfo(String key);

    @Delete("update tb_book set bookname = #{bookName} , author = #{author} where book_id = #{bookId}")
    void updateBook(Book updateBook);

    @Select("select * from tb_book where book_id = #{id}")
    Book queryBookById(int id);

    @Insert("insert into tb_book(bookname,author) values(#{bookName},#{author}) ")
    void addBook(Book book);

    @Delete("delete from tb_book where book_id = #{id}")
    void deleteBook(int id);

//    select t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id;
    @Select("select t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id ")
    List<BookDto> queryAllBorrowBook();

//    select t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id where name = '小张';
    @Select("select t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id where name like #{name}")
    List<BookDto> querryOneBorrowBook(String name);

    @Select("select * from tb_book where book_id = #{id}")
    Book queryBookIfBorrow(int id);

// update tb_book set name_id = xxx where book_id = userid;
    @Update("update tb_book set name_id = #{userId} where book_id = #{bookId}")
    void borrowBook(@Param("bookId") int bookId, @Param("userId") int userId);

    //select * from tb_book where name_id = 2 and book_id = 7;
    @Select("select * from tb_book where name_id = #{userId} and book_id = #{bookId}")
    Book queryIfUserBorrowThisBook(@Param("userId") int userId, @Param("bookId") int bookId);

    //
    @Update("update tb_book set name_id = 0 where book_id= #{bookId}")
    void returnBook(int bookId);

    //select t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id where name_id = 2 ;
    @Select("select t1.book_id , t1.bookname,t1.author,t2.name from tb_book t1 join tb_user t2 on t1.name_id = t2.user_id where name_id = #{userId}")
    List<BookDto> queryUserBorrowBooks(int userId);
}
