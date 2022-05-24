package com.book.shop.book.controller.adminController;

import com.book.shop.book.domain.Admin;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.adminService.AdminManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/6 14:29
 * @veersion 1.0
 */
@Controller
@RequestMapping("/jsp/admin")
public class AdminManageController {
    @Resource
    private AdminManageService adminManageService;

    @RequestMapping("/queryAllAdmin.do")
    public ModelAndView queryAllAdmin(String page, HttpServletRequest request) {
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        //获取xml中设置的每页显示大小参数
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pageBean = new PageBean(curPage, maxSize, adminManageService.bookReadCount());
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminList",adminManageService.queryAllAdmin(pageBean));
        mv.addObject("pageBean",pageBean);
        mv.setViewName("adminManage/adminList.jsp");
        return mv;
    }

    @RequestMapping("/adminAdd.do")
    public ModelAndView adminAdd(Admin admin,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        //添加之前判断用户名是否在库中存在
        if (adminManageService.findAdminByName(admin.getUserName())) {
            mv.addObject("adminMessage", "用户添加失败！用户名已存在");
            mv.setViewName("adminManage/adminAdd.jsp");
        } else {
            if (adminManageService.userAdd(admin)) {
                mv.addObject("adminMessage", "用户添加成功！");
                mv.setViewName("queryAllAdmin.do");
            } else {
                mv.addObject("adminMessage", "用户添加失败！");
                mv.setViewName("adminManage/adminAdd.jsp");
            }
        }
        return mv;
    }

    @RequestMapping("/adminIsExist.do")
    @ResponseBody
    public Map<String,String> adminIsExist(String param){
        String userName = param;
        Map<String,String> map = new HashMap<>();
        if (adminManageService.findAdminByName(userName)) {
            map.put("info", "用户名已存在");
            map.put("status", "n");
        } else {
            map.put("info", "用户名可以使用");
            map.put("status", "y");
        }
        return map;
    }

    @RequestMapping("/adminUserDelete.do")
    public ModelAndView adminUserDelete(String id) {
        ModelAndView mv = new ModelAndView();
        int adminId = Integer.parseInt(id);
        if (adminManageService.adminUserDelete(adminId)) {
            mv.addObject("adminMessage", "用户已删除");
        } else {
            mv.addObject("adminMessage", "用户删除失败");
        }
        mv.setViewName("queryAllAdmin.do");
        return mv;
    }

    @RequestMapping("/adminUserEdit.do")
    public ModelAndView adminUserEdit(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminInfo", adminManageService.findAdminById(Integer.valueOf(id)));
        mv.setViewName("adminManage/adminEdit.jsp");
        return mv;
    }

    @RequestMapping("/adminUserUpdate.do")
    public ModelAndView adminUserUpdate(Admin admin) {
        ModelAndView mv = new ModelAndView();
        if (adminManageService.adminUserUpdate(admin)) {
            mv.addObject("adminMessage", "用户更新成功");
            mv.setViewName("queryAllAdmin.do");
        } else {
            //更新失败跳转到修改页面
            mv.addObject("adminMessage", "用户更新失败");
            mv.addObject("adminInfo", adminManageService.findAdminById(Integer.valueOf(admin.getId())));//这里回去是Admin对象
            mv.setViewName("adminManage/adminEdit.jsp");
        }
        return mv;
    }

    @RequestMapping("/adminUserDeletes.do")
    public ModelAndView adminUserDeletes(String ids[]) {
        ModelAndView mv = new ModelAndView();
        boolean flag = false;
        for (String id : ids) {
            flag = adminManageService.adminUserDelete(Integer.parseInt(id));
        }
        if (flag) {
            mv.addObject("adminMessage", "用户已批量删除");
        } else {
            mv.addObject("adminMessage", "用户批量删除失败");
        }
        //用户删除成功失败都跳转到用户列表页面
        mv.setViewName("queryAllAdmin.do");
        return mv;
    }

}
