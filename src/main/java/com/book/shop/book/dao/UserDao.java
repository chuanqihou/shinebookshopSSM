package com.book.shop.book.dao;

import com.book.shop.book.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/4 11:29
 * @veersion 1.0
 */
public interface UserDao {
    User userLogin(User user);

    int addUser(User user);

    long bookReadCount();

    List<User> queryAllUser(Map<String, Integer> map);

    int userDeleteById(String id);

    User userEditById(Integer userId);

    int userUpdateById(User user);

    int findUserByName(String userName);

    int userDeletesByIds(String ids);

    long searchUserByUserNameCount(String username);

    List<User> searchUserByUserNameList(Map<String, Object> map);

    User findUserByUserId(int userId);

    int userUpdate(User user);
}
