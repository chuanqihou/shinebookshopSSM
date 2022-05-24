package com.book.shop.book.service.bookService.impl;

import com.book.shop.book.dao.BookDao;
import com.book.shop.book.dao.CatalogDao;
import com.book.shop.book.dao.OrderDao;
import com.book.shop.book.dao.OrderItemDao;
import com.book.shop.book.domain.*;
import com.book.shop.book.service.bookService.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/11/30 14:57
 * @veersion 1.0
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;
    @Resource
    private CatalogDao catalogDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private OrderDao orderDao;

    /**
     * 获取首页展示商品图书
     * @param num
     * @return
     */
    @Override
    public List<ViewBook> bookList(int num) {
        List<ViewBook> list = bookDao.bookList(num);
        return list;
    }

    @Override
    public List<ViewBook> newBooks(int num) {
        List<ViewBook> list = bookDao.newBooks(num);
        return list;
    }

    /**
     * 按分类获取图书数量
     */
    @Override
    public long sortBookReadCount(int catalogId) {
        long count = bookDao.sortBookReadCount(catalogId);
        return count;
    }

    /**
     * 按分类获取图书分页列表(视图)
     * @param pageBean
     * @param catalogId
     * @return
     */
    @Override
    public List<ViewBook> sortIdBookList(PageBean pageBean, int catalogId){
        Integer i = (pageBean.getCurPage() - 1) * pageBean.getMaxSize();
        Integer j = pageBean.getMaxSize();
        Map<String, Integer> map = new HashMap<>();
        map.put("catalogId",catalogId);
        map.put("i",i);
        map.put("j",j);
        List<ViewBook> viewBooks = bookDao.sortIdBookList(map);
        return viewBooks;
    }

    /**
     * 获取图书总数
     * @return
     */
    @Override
    public long bookReadCount() {
        return bookDao.bookReadCount();
    }

    /**
     * 获取图书分页列表(视图)
     * @param pageBean
     * @return
     */
    @Override
    public List<ViewBook> PageBookList(PageBean pageBean) {
        int i = (pageBean.getCurPage() - 1) * pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        Map<String, Integer> map = new HashMap<>();
        map.put("i",i);
        map.put("j",j);
        return bookDao.PageBookList(map);
    }

    @Override
    public ViewBook findBookById(int bookIdInt) {
        ViewBook viewbook = bookDao.findBookById(bookIdInt);
        return viewbook;
    }

    @Override
    public long bookReadCountByBookName(String bookName) {
        int count = bookDao.bookReadCountByBookName(bookName);
        return count;
    }

    @Override
    public List<ViewBook> bookListByBookName(PageBean pb, String bookName) {
        int i = (pb.getCurPage() - 1) * pb.getMaxSize();
        int j = pb.getMaxSize();
        Map<String,Object> map = new HashMap<>();
        map.put("i", i);
        map.put("j", j);
        map.put("bookName",bookName);
        List<ViewBook> list = bookDao.bookListByBookName(map);
        return list;
    }

    @Override
    public ViewBook OrderFindBookById(int bookId) {
        ViewBook book = bookDao.findBookById(bookId);
        return book;
    }

    /**
     * 获取图书分类信息（包括类别图书的数量）
     * @return
     */
    @Override
    public List<Catalog> getCatalog() {
        List<Catalog> catalogList = catalogDao.getCatalog();
        return catalogList;
    }

    public boolean orderItemAdd(OrderItem oi) {
        int i = orderItemDao.orderItemAdd(oi);
        return i>0?true:false;
    }

    public List<OrderItem> findItemByOrderId(int orderId) {
        List<OrderItem> orderItems = orderItemDao.findItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public boolean orderAdd(Order order) {
        int result = orderDao.orderAdd(order);
        return result>0?true:false;
    }

    @Override
    public int findOrderIdByOrderNum(String orderNum) {
        int orderId=0;
        orderId = orderDao.findOrderIdByOrderNum(orderNum);
        return orderId;
    }

    @Override
    public long orderReadCount(int userId) {
        long result = orderDao.orderReadCount(userId);
        return result;
    }

    @Override
    public List<Order> viewOrder(PageBean pb, int userId) {
        int i = (pb.getCurPage()-1)*pb.getMaxSize();
        int j = pb.getMaxSize();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("i", i);
        map.put("j", j);
        List<Order> orders = orderDao.viewOrder(map);
        return orders;
    }
}
