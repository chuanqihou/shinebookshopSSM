package com.book.shop.book.dao;

import com.book.shop.book.domain.OrderItem;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/4 14:46
 * @veersion 1.0
 */
public interface OrderItemDao {
    int orderItemAdd(OrderItem oi);

    List<OrderItem> findItemByOrderId(int orderId);

    int deleteByOrderId(int orderId);

    void deleteByBookId(int bookId);

    OrderItem queryItemByOrderId(int orderId);

    List<Integer> getOrderIdByBookId(String id);
}
