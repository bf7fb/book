package com.wangzhen.dto;

/**
 * @author wz
 * @ClassName BookDto
 * @date 2023/6/13 15:43
 * @Description TODO
 */
public class BookDto {
    private int bookId;
    private String name;
    private String bookName;
    private String author;
    public BookDto(int bookId, String name, String bookName, String author) {
        this.bookId = bookId;
        this.name = name;
        this.bookName = bookName;
        this.author = author;
    }
    public BookDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public BookDto(String name, String bookName, String author) {
        this.name = name;
        this.bookName = bookName;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
