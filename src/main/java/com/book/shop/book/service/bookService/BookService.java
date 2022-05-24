package com.book.shop.book.service.bookService;
import com.book.shop.book.domain.*;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/11/30 14:45
 * @veersion 1.0
 */
public interface BookService {

    //获取首页展示商品图书
    List<ViewBook> bookList(int num);
    List<ViewBook> newBooks(int num);

    //按分类获取图书数量
    long sortBookReadCount(int catalogId);

    // 按分类获取图书分页列表(视图)
    List<ViewBook> sortIdBookList(PageBean pageBean, int catalogId);

    // 获取图书总数
    long bookReadCount();

    // 获取图书分页列表(视图)
    List<ViewBook> PageBookList(PageBean pageBean);

    // 根据图书id查找图书信息
    ViewBook findBookById(int bookIdInt);

    long bookReadCountByBookName(String bookName);

    List<ViewBook> bookListByBookName(PageBean pb, String bookName);

    ViewBook OrderFindBookById(int bookId);

    List<Catalog> getCatalog();

    boolean orderItemAdd(OrderItem oi);

    List<OrderItem> findItemByOrderId(int orderId);

    boolean orderAdd(Order order);

    int findOrderIdByOrderNum(String orderNum);

    long orderReadCount(int userId);

    List<Order> viewOrder(PageBean pb, int userId);
}
