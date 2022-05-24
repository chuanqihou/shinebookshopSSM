package com.book.shop.book.service.bookService;

import com.book.shop.book.domain.Order;
import com.book.shop.book.domain.PageBean;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/4 14:48
 * @veersion 1.0
 */
public interface OrderService {
    boolean orderAdd(Order order);

    int findOrderIdByOrderNum(String orderNum);

    long orderReadCount(int userId);

    List<Order> viewOrder(PageBean pb, int userId);
}
