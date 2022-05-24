package com.book.shop.book.controller.adminController;

import com.book.shop.book.domain.*;
import com.book.shop.book.service.adminService.BookManageService;
import com.book.shop.book.service.adminService.OrderManageService;
import com.book.shop.book.service.bookService.OrderItemService;
import com.book.shop.book.service.bookService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/29 10:32
 * @veersion 1.0
 */
@Controller
@RequestMapping("/jsp/admin")
public class OrderManageController {
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private UserService userService;
    @Resource
    private BookManageService bookManageService;

    @RequestMapping("/queryAllOrder.do")
    public ModelAndView queryAllOrder(String page) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 8;
        PageBean pb = new PageBean(curPage, maxSize, orderManageService.orderAllCount());
        mv.addObject("pageBean", pb);
        mv.addObject("orderList", orderManageService.queryOrder(pb));
        mv.setViewName("orderManage/orderlist.jsp");
        return mv;
    }

    @RequestMapping("/queryOrderHandle.do")
    public ModelAndView queryOrderHandle(String page, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = new PageBean(curPage, maxSize, orderManageService.queryOrderHandleCount(1));
        mv.addObject("pageBean", pb);
        mv.addObject("orderList", orderManageService.queryOrderHandle(pb,1));
        mv.setViewName("orderManage/orderOp.jsp");
        return mv;
    }

    @RequestMapping("/findOrderByName.do")
    public ModelAndView findOrderByName(String page,String ordername,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = null;
        if(ordername != null && ordername != "") {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCountByName(ordername));
            mv.addObject("orderList", orderManageService.queryOrderByOrderName(pb,ordername));
        }else {
            pb = new PageBean(curPage, maxSize, orderManageService.orderAllCount());
            mv.addObject("orderList", orderManageService.queryOrder(pb));
        }
        mv.addObject("pageBean", pb);
        mv.setViewName("orderManage/orderlist.jsp");
        return mv;
    }

    @RequestMapping("/orderDetail.do")
    public ModelAndView orderDetail(String id) {
        ModelAndView mv = new ModelAndView();
        Order order = orderManageService.findOrderByOrderId(id);
        User user = userService.findUserByUserId(order.getUserId());
        List<OrderItem> orderItem = orderItemService.findItemByOrderId(order.getOrderId());
            //通过图书id获取图书对象
        List<ViewBook> viewBooks = new ArrayList<>();
        for (OrderItem o : orderItem) {
            ViewBook viewBook = bookManageService.findViewBookById(o.getBookId());
            viewBooks.add(viewBook);
        }
        mv.addObject("order", order);
        mv.addObject("user", user);
        mv.addObject("orderItem",orderItem.get(0));
        mv.addObject("viewBook", viewBooks);
        mv.setViewName("orderManage/orderDetail.jsp");
        return mv;
    }

    @RequestMapping("/orderDelete.do")
    public ModelAndView orderDelete(String id) {
        ModelAndView mv = new ModelAndView();
        if(id != null && id != "") {
            orderItemService.deleteOrderItem(Integer.valueOf(id));
            orderManageService.deleteOrderByOrderId(Integer.valueOf(id));
        }
        mv.setViewName("queryAllOrder.do");
        return mv;
    }

    @RequestMapping("/findOrderDealByName.do")
    public ModelAndView findOrderDealByName(String page,String ordername,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = null;

        if(ordername != null && ordername != "") {
            pb = new PageBean(curPage, maxSize, orderManageService.orderReadCountByNameAndStatus(1,ordername));
            request.setAttribute("orderList", orderManageService.queryOrderByOrderNameAndStatus(pb,1,ordername));
        }else {
            pb = new PageBean(curPage, maxSize, orderManageService.orderAllCountByStatus(1));
            request.setAttribute("orderList", orderManageService.queryOrderByStatus(pb,1));
        }
        mv.addObject("pageBean", pb);
        mv.setViewName("orderManage/orderlist.jsp");
        return mv;
    }

    @RequestMapping("/orderProcessing.do")
    public ModelAndView orderProcessing(String id,String page,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer i = -1;
        if (id!=null){
            i = Integer.parseInt(id);
        }
        if(orderManageService.orderChangeStatus(i,2)) {
            mv.addObject("orderMessage", "一个订单操作成功");
        }else {
            mv.addObject("orderMessage", "一个订单操作失败");
        }
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = new PageBean(curPage, maxSize, orderManageService.orderAllCountByStatus(1));
        mv.addObject("pageBean", pb);
        mv.addObject("orderList", orderManageService.queryOrderByStatus(pb,1));
        mv.setViewName("orderManage/orderOp.jsp");
        return mv;
    }
}
