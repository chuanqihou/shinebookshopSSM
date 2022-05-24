package com.book.shop.book.service.bookService;

import com.book.shop.book.domain.OrderItem;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/4 14:48
 * @veersion 1.0
 */
public interface OrderItemService {
    boolean orderItemAdd(OrderItem oi);

    List<OrderItem> findItemByOrderId(int orderId);

    OrderItem queryItemByOrderId(int orderId);

    void deleteOrderItem(Integer valueOf);
}
