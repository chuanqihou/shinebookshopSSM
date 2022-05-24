package com.book.shop.book.service.adminService;

import com.book.shop.book.domain.Order;
import com.book.shop.book.domain.PageBean;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/29 20:22
 * @veersion 1.0
 */
public interface OrderManageService {
    long orderAllCount();

    List<Order> queryOrder(PageBean pb);

    long queryOrderHandleCount(int i);

    List<Order> queryOrderHandle(PageBean pb, int i);

    long orderReadCountByName(String ordername);

    Object queryOrderByOrderName(PageBean pb, String ordername);

    Order findOrderByOrderId(String id);

    void deleteOrderByOrderId(Integer orderId);

    long orderReadCountByNameAndStatus(int i, String ordername);

    List<Order> queryOrderByOrderNameAndStatus(PageBean pb, int i, String ordername);

    long orderAllCountByStatus(int i);

    List<Order> queryOrderByStatus(PageBean pb, int i);

    boolean orderChangeStatus(Integer id, int status);
}
