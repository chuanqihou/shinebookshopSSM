package com.book.shop.book.service.bookService.impl;

import com.book.shop.book.dao.OrderDao;
import com.book.shop.book.dao.OrderItemDao;
import com.book.shop.book.dao.UserDao;
import com.book.shop.book.domain.Order;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.User;
import com.book.shop.book.service.bookService.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/4 11:28
 * @veersion 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderItemDao orderItemDao;

    @Override
    public User userLogin(User user) {
        User user2 = userDao.userLogin(user);
        return user2;
    }

    @Override
    public boolean userAdd(User user) {
        int result = userDao.addUser(user);
        return result>0 ? true :false;
    }

    @Override
    public long bookReadCount() {
        return userDao.bookReadCount();
    }

    @Override
    public List<User> queryAllUser(PageBean pageBean) {
        int i = (pageBean.getCurPage()-1)*pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        Map<String, Integer> map = new HashMap<>();
        map.put("i",i);
        map.put("j",j);
        return userDao.queryAllUser(map);
    }

    @Override
    public boolean userDeleteById(String id) {
        List<Order> orders = orderDao.queryOrderByUserId(id);
        for (Order order : orders) {
            orderItemDao.deleteByOrderId(order.getOrderId());
        }
        orderDao.deleteByUserId(id);
        int result = userDao.userDeleteById(id);
        return result>0 ?true:false;
    }

    @Override
    public User userEditById(Integer userId) {
        return userDao.userEditById(userId);
    }

    @Override
    public boolean userUpdateById(User user) {
        int result = userDao.userUpdateById(user);
        return result>0 ?true:false;
    }

    @Override
    public boolean findUserByName(String userName) {
        int result = userDao.findUserByName(userName);
        return result>0?true:false;
    }

    @Override
    public boolean userDeletesByIds(String ids) {
        List<Order> orders = orderDao.findOrderByUserIds(ids);
        for (Order order : orders) {
            orderItemDao.deleteByOrderId(order.getOrderId());
        }
        orderDao.deleteByUserIds(ids);
        int result = userDao.userDeletesByIds(ids);
        return result>0?true:false;
    }

    @Override
    public long searchUserByUserNameCount(String username) {
        return userDao.searchUserByUserNameCount(username);
    }

    @Override
    public List<User> searchUserByUserNameList(PageBean pageBean, String username) {
        int i = (pageBean.getCurPage()-1)*pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        Map<String, Object> map = new HashMap<>();
        map.put("i",i);
        map.put("j",j);
        map.put("userName",username);
        return userDao.searchUserByUserNameList(map);
    }

    @Override
    public User findUserByUserId(int userId) {
        return userDao.findUserByUserId(userId);
    }

    @Override
    public boolean userUpdate(User user) {
        int result = userDao.userUpdate(user);
        return result>0 ?true:false;
    }
}
