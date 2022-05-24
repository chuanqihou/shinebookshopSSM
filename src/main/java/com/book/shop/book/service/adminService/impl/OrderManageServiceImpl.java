package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.OrderDao;
import com.book.shop.book.domain.Order;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.adminService.OrderManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/29 20:23
 * @veersion 1.0
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Resource
    private OrderDao orderDao;

    @Override
    public long orderAllCount() {
        int result = orderDao.orderAllCount();
        return result;
    }

    @Override
    public List<Order> queryOrder(PageBean pageBean) {
        Map<String, Integer> map = new HashMap<>();
        map.put("i",(pageBean.getCurPage()-1)*pageBean.getMaxSize());
        map.put("j",pageBean.getMaxSize());
        List<Order> orders = orderDao.queryOrder(map);
        return orders;
    }

    @Override
    public long queryOrderHandleCount(int i) {
        int result = orderDao.queryOrderHandleCount(i);
        return result>0?result:0;
    }

    @Override
    public List<Order> queryOrderHandle(PageBean pageBean, int status) {

        String sql="select * from s_order where orderStatus=? limit ?,?";

        Map<String, Integer> map = new HashMap<>();
        map.put("i",(pageBean.getCurPage()-1)*pageBean.getMaxSize());
        map.put("j",pageBean.getMaxSize());
        map.put("status",status);
        List<Order> orders = orderDao.queryOrderByStatus(map);

        return orders;
    }

    @Override
    public long orderReadCountByName(String ordername) {
        long result = orderDao.orderReadCountByName(ordername);
        return result;
    }

    @Override
    public List<Order> queryOrderByOrderName(PageBean pageBean, String ordername) {
        Map<String, Object> map = new HashMap<>();
        int i = (pageBean.getCurPage()-1)*pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        map.put("i",i);
        map.put("j",j);
        map.put("ordername",ordername);
        List<Order> orders = orderDao.queryOrderByOrderName(map);

        return orders;
    }

    @Override
    public Order findOrderByOrderId(String id) {
        return orderDao.findOrderByOrderId(id);
    }

    @Override
    public void deleteOrderByOrderId(Integer orderId) {
        orderDao.deleteOrderByOrderId(orderId);
    }

    @Override
    public long orderReadCountByNameAndStatus(int i, String ordername) {
        Map<String, Object> map = new HashMap<>();
        map.put("status",i);
        map.put("orderName",ordername);
        long result = orderDao.orderReadCountByNameAndStatus(map);
        return result;
    }

    @Override
    public List<Order> queryOrderByOrderNameAndStatus(PageBean pageBean, int status, String ordername) {
        Map<String, Object> map = new HashMap<>();
        int i = (pageBean.getCurPage()-1)*pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        map.put("i",i);
        map.put("j",j);
        map.put("ordername",ordername);
        map.put("status",status);
        List<Order> orders = orderDao.queryOrderByOrderNameAndStatus(map);
        return orders;
    }

    @Override
    public long orderAllCountByStatus(int status) {
        int result = orderDao.orderAllCountByStatus(status);
        return result;
    }

    @Override
    public List<Order> queryOrderByStatus(PageBean pageBean, int status) {
        Map<String, Integer> map = new HashMap<>();
        map.put("i",(pageBean.getCurPage()-1)*pageBean.getMaxSize());
        map.put("j",pageBean.getMaxSize());
        map.put("status",status);
        List<Order> orders = orderDao.queryOrderByStatus(map);
        return orders;
    }

    @Override
    public boolean orderChangeStatus(Integer id, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        int result = orderDao.orderChangeStatus(map);
        return result>0?true:false;
    }


}
