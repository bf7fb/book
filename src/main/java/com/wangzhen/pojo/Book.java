package com.wangzhen.pojo;

/**
 * @author wz
 * @ClassName Book
 * @date 2023/6/12 16:50
 * @Description TODO
 */
public class Book {
    /**
     * 书籍ID
     */
    private Integer bookId;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 书作者
     */
    private String author;
    /**
     * 借书人Id
     */
    private int nameId;

    public Book() {
    }

    public Book(Integer bookId, String bookName, String author, int nameId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.nameId = nameId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", nameId=" + nameId +
                '}';
    }
}
