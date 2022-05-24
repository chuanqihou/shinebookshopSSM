package com.book.shop.book.dao;


import com.book.shop.book.domain.Book;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.ViewBook;

import java.util.List;
import java.util.Map;

public interface BookDao {

	// 随机获取指定数量书
	List<ViewBook> bookList(int num);

	// 获取指定数量新添加的图书
	List<ViewBook> newBooks(int num);

	// 获取图书总数
	long bookReadCount();

	// 按分类获取图书数量
	long sortBookReadCount(int catalogId);

	//按分类获取图书分页列表(视图)
	List<ViewBook> sortIdBookList(Map<String, Integer> map);

	//获取图书分页列表(视图)
	List<ViewBook> PageBookList(Map<String, Integer> map);

	// 根据图书id查找图书信息(视图)
	ViewBook findBookById(int bookId);


	int bookReadCountByBookName(String bookName);


	List<ViewBook> bookListByBookName(Map<String,Object> map);

	ViewBook OrderFindBookById(int bookId);

    Book queryBookById(int bookId);

	int deleteBookByBookId(int bookId);

    int bookAdd(Book book);

    int findBookByBookName(String bookName);

    int bookUpdate(Book book);

    void deleteBookByCatalogId(String id);

    List<ViewBook> findViewsBookById(int bookId);

	List<Integer> queryBookIdByCatalogId(String id);
}
