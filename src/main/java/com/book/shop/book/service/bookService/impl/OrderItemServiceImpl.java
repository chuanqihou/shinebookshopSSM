package com.book.shop.book.service.bookService.impl;

import com.book.shop.book.dao.OrderItemDao;
import com.book.shop.book.domain.OrderItem;
import com.book.shop.book.service.bookService.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/4 15:05
 * @veersion 1.0
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemDao orderItemDao;

    public boolean orderItemAdd(OrderItem oi) {
        int i = orderItemDao.orderItemAdd(oi);
        return i>0?true:false;
    }

    @Override
    public List<OrderItem> findItemByOrderId(int orderId) {
        List<OrderItem> orderItems = orderItemDao.findItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public OrderItem queryItemByOrderId(int orderId) {
        return orderItemDao.queryItemByOrderId(orderId);
    }

    @Override
    public void deleteOrderItem(Integer orderId) {
        orderItemDao.deleteByOrderId(orderId);
    }
}
