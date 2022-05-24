package com.book.shop.book.service.bookService;

import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.User;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/4 11:28
 * @veersion 1.0
 */
public interface UserService {
    User userLogin(User user);

    boolean userAdd(User user);

    long bookReadCount();

    List<User> queryAllUser(PageBean pageBean);

    boolean userDeleteById(String id);

    User userEditById(Integer userId);

    boolean userUpdateById(User user);

    boolean findUserByName(String userName);

    boolean userDeletesByIds(String ids);

    long searchUserByUserNameCount(String username);

    List<User> searchUserByUserNameList(PageBean pageBean, String username);

    User findUserByUserId(int userId);

    boolean userUpdate(User user);
}
