package com.book.shop.book.controller.adminController;

import com.book.shop.book.domain.Admin;
import com.book.shop.book.service.adminService.AdminLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/6 13:35
 * @veersion 1.0
 */
@Controller
public class AdminLoginController {

    @Resource
    private AdminLoginService adminLoginService;

    @RequestMapping("/adminLogin.do")
    public ModelAndView adminLogin(Admin admin, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        List<String> list=new ArrayList<String>();
        if(admin.getUserName()==null) {
            list.add("用户名不能为空");
        }
        if(admin.getPassWord()==null) {
            list.add("密码不能为空");
        }
        if(list.size()==0) {
            if(adminLoginService.adminLogin(admin)) {
                request.getSession().setAttribute("adminUser",admin );
                mv.setViewName("redirect:jsp/admin/index.jsp");
                return mv;
            }else {
                list.add("用户名或密码错误!请重新输入");
            }
        }
        mv.addObject("infoList",list);
        mv.setViewName("jsp/admin/login.jsp");
        return mv;
    }

    @RequestMapping("/adminSignOut.do")
    public String adminSignOut(HttpServletRequest request) {
        request.getSession().removeAttribute("admin_user");
        return "jsp/admin/login.jsp";
    }
}
