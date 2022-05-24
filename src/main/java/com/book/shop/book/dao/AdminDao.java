package com.book.shop.book.dao;

import com.book.shop.book.domain.Admin;

import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/6 13:48
 * @veersion 1.0
 */
public interface AdminDao {
    Admin adminLogin(Admin admin);

    int updateLastLoginTime(Map<String, Object> map);

    long bookReadCount();

    List<Admin> queryAllAdmin(Map<String, Integer> map);

    int findAdminByName(String userName);

    int userAdd(Admin admin);

    int adminUserDelete(int adminId);

    Admin findAdminById(Integer adminId);

    int adminUserUpdate(Admin admin);

    int adminUserDeletes(String ids);
}
