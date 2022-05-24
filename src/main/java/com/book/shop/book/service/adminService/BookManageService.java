package com.book.shop.book.service.adminService;

import com.book.shop.book.domain.Book;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.ViewBook;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/8 13:58
 * @veersion 1.0
 */
public interface BookManageService {
    Book queryBookById(int idInt);

    boolean deleteBookByBookId(int idInt);

    boolean deleteImgById(int imgId);

    boolean bookAdd(Book book);

    boolean findBookByBookName(String bookName);

    ViewBook findViewBookById(int id);

    long bookReadCountByBookName(String bookname);

    Object bookListByBookName(PageBean pb, String bookname);

    long bookReadCount();

    Object PageBookList(PageBean pb);

    Book findBookById(int i);

    boolean bookUpdate(Book book);

    List<ViewBook> findViewsBookById(int bookId);
}
