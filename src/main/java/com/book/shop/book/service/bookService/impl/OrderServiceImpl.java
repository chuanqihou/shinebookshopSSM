package com.book.shop.book.service.bookService.impl;

import com.book.shop.book.dao.OrderDao;
import com.book.shop.book.domain.Order;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.bookService.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/4 14:54
 * @veersion 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

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
