package com.book.shop.filter;

import com.book.shop.book.domain.Admin;
import com.book.shop.book.domain.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther 传奇后
 * @date 2022/1/1 15:01
 * @veersion 1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取来访地址
        String path = request.getServletPath();
        System.out.println("path"+path);

        if (path.contains("jsp/admin") || path.contains("adminUser")){
            if (path.contains("login.jsp") || path.contains("Login.do")){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                //获取session中是否包含user属性
                Admin admin = (Admin) request.getSession().getAttribute("adminUser");
                //包含则放行
                if (admin != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    //为空则跳转到登录界面
                    response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
                }
            }
        }else {
            //判断来访地址
            if (!(path.contains("conorder.jsp") || path.contains("myorderlist.jsp")
                    || path.contains("ordersuccess.jsp") || path.contains("userMessage.jsp")
                    || path.contains("submitOrder.do")
                    || path.contains("viewOrder.do") || path.contains("confirmReceipt.do"))) {
                //包括则放行
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //获取session中是否包含user属性
                User user = (User) request.getSession().getAttribute("landing");
                //包含则放行
                if (user != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    //为空则跳转到登录界面
                    response.sendRedirect(request.getContextPath() + "/jsp/book/index.jsp");
                }
            }
        }
    }
}
