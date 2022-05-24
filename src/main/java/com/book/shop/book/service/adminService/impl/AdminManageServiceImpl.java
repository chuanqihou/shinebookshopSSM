package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.AdminDao;
import com.book.shop.book.domain.Admin;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.adminService.AdminManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/6 14:37
 * @veersion 1.0
 */
@Service
public class AdminManageServiceImpl implements AdminManageService {
    @Resource
    private AdminDao adminDao;
    @Override
    public long bookReadCount() {
        long count=0;
        count = adminDao.bookReadCount();
        return count;
    }

    @Override
    public List<Admin> queryAllAdmin(PageBean pageBean) {
        int i = (pageBean.getCurPage()-1)*pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        Map<String,Integer> map = new HashMap<>();
        map.put("i", i);
        map.put("j", j);
        List<Admin> admins = adminDao.queryAllAdmin(map);
        return admins;
    }

    @Override
    public boolean findAdminByName(String userName) {
        int result = adminDao.findAdminByName(userName);
        return result>0 ? true:false;
    }

    @Override
    public boolean userAdd(Admin admin) {
        int result = adminDao.userAdd(admin);
        return result==1 ? true:false;
    }

    @Override
    public boolean adminUserDelete(int adminId) {
        int result = adminDao.adminUserDelete(adminId);
        return result>0 ? true:false;
    }

    @Override
    public Admin findAdminById(Integer adminId) {
        return adminDao.findAdminById(adminId);
    }

    @Override
    public boolean adminUserUpdate(Admin admin) {
        int result = adminDao.adminUserUpdate(admin);
        return result==1 ? true:false;
    }

    @Override
    public boolean adminUserDeletes(String ids) {
        int result = adminDao.adminUserDeletes(ids);
        return result>0 ?true:false;
    }
}
