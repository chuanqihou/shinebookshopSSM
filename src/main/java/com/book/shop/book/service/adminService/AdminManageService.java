package com.book.shop.book.service.adminService;

import com.book.shop.book.domain.Admin;
import com.book.shop.book.domain.PageBean;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/6 14:31
 * @veersion 1.0
 */
public interface AdminManageService {
    long bookReadCount();

    List<Admin> queryAllAdmin(PageBean pageBean);

    boolean findAdminByName(String userName);

    boolean userAdd(Admin admin);

    boolean adminUserDelete(int adminId);

    Admin findAdminById(Integer adminId);

    boolean adminUserUpdate(Admin admin);

    boolean adminUserDeletes(String ids);
}
