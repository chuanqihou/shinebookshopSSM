package com.book.shop.book.dao;

import com.book.shop.book.domain.Order;

import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/4 14:46
 * @veersion 1.0
 */
public interface OrderDao {
    int orderAdd(Order order);

    int findOrderIdByOrderNum(String orderNum);

    long orderReadCount(int userId);

    List<Order> viewOrder(Map<String, Object> map);

    List<Order> queryOrderByUserId(String id);

    void deleteByUserId(String id);

    List<Order> findOrderByUserIds(String ids);

    int deleteByUserIds(String ids);

    int orderAllCount();

    List<Order> queryOrder(Map<String, Integer> map);

    int queryOrderHandleCount(int i);

    List<Order> queryOrderByStatus(Map<String, Integer> map);

    long orderReadCountByName(String ordername);

    List<Order> queryOrderByOrderName(Map<String, Object> map);

    Order findOrderByOrderId(String id);

    void deleteOrderByOrderId(Integer orderId);

    long orderReadCountByNameAndStatus(Map<String, Object> map);

    List<Order> queryOrderByOrderNameAndStatus(Map<String, Object> map);

    int orderAllCountByStatus(int status);

    int orderChangeStatus(Map<String, Object> map);
}
