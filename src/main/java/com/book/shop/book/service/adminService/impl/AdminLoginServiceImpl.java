package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.AdminDao;
import com.book.shop.book.domain.Admin;
import com.book.shop.book.service.adminService.AdminLoginService;
import com.book.shop.utill.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/6 13:46
 * @veersion 1.0
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Resource
    private AdminDao adminDao;

    @Override
    public boolean adminLogin(Admin admin) {
        boolean flag = false;
        Admin findAdmin = adminDao.adminLogin(admin);
        if (findAdmin != null) {
            String time = DateUtil.show();
            Integer adminId = findAdmin.getId();
            Map<String,Object> map = new HashMap<>();
            map.put("time", time);
            map.put("adminId", adminId);
            int updateResult = adminDao.updateLastLoginTime(map);
            if (updateResult == 1) {
                flag = true;
            }
        }
        return flag;
    }
}
