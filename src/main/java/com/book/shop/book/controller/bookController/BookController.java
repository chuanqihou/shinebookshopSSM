package com.book.shop.book.controller.bookController;
import com.book.shop.book.domain.*;
import com.book.shop.book.service.adminService.OrderManageService;
import com.book.shop.book.service.bookService.BookService;
import com.book.shop.utill.DateUtil;
import com.book.shop.utill.RanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/11/30 14:27
 * @veersion 1.0
 */
@Controller
public class BookController {
    @Resource
    private BookService bookService;
    @Resource
    private OrderManageService orderManageService;

    /**
     * 获取图书分类信息（包括类别图书的数量）
     * @return
     */
    @RequestMapping("/GetCatalog.do")
    @ResponseBody
    public List<Catalog> getCatalog(){
        List<Catalog> catalog = bookService.getCatalog();
        //这里返回查询每个分类的数量
        for(int i=0;i<catalog.size();i++) {
            Catalog c = catalog.get(i);
            long size=bookService.sortBookReadCount(c.getCatalogId());
            c.setCatalogSize(size);
        }
        return catalog;
    }

    /**
     * 获取首页展示商品图书
     * @return
     */
    @RequestMapping("/ShopIndex.do")
    @ResponseBody
    public Map<String, List<ViewBook>> shopIndex(){
        List<ViewBook> recBooks = bookService.bookList(4);
        List<ViewBook> newBooks = bookService.newBooks(4);
        Map<String, List<ViewBook>> result = new HashMap<>();
        result.put("recBooks",recBooks);
        result.put("newBooks",newBooks);
        return result;
    }

    /**
     * 图书分类查询以及分类
     * @param action
     * @param catalogId
     * @param page
     * @return
     */
    @RequestMapping("/BookList.do")
    public ModelAndView bookList(String action,String catalogId,String page){
        System.out.println("开始");
        String c = catalogId;
        System.out.println(c);
        ModelAndView mv = new ModelAndView();
        if (action==null){
            action = "list";
        }
        if (action.equals("list")) {
            int curPage = 1;
            if (page != null) {
                curPage = Integer.parseInt(page);
            }
            PageBean pb=null;
            List<ViewBook> bookList = null;
            //获取有没有分类id，没有就是查全部
            if(catalogId!=null) {
                int catalogIdInt=Integer.parseInt(catalogId);
                pb = new PageBean(curPage, 12, bookService.sortBookReadCount(catalogIdInt));
                bookList = bookService.sortIdBookList(pb,Integer.parseInt(catalogId));
                if(bookList.size()>0) {
                    //从返回的分类集合中第一个获取数据的分类
                    mv.addObject("title",bookList.get(0).getCatalogName());
                }
            }else {
                pb = new PageBean(curPage, 12, bookService.bookReadCount());
                bookList = bookService.PageBookList(pb);
                mv.addObject("title", "所有图书");
            }
            mv.addObject("pageBean", pb);
            mv.addObject("bookList",bookList);
            mv.setViewName("jsp/book/booklist.jsp");
        }
        System.out.println("结束");
        return mv;
    }

    @RequestMapping("/bookdetail.do")
    public ModelAndView bookTail(String bookId){
        int bookIdInt = Integer.parseInt(bookId);
        ViewBook viewbook = bookService.findBookById(bookIdInt);
        ModelAndView mv = new ModelAndView();
        mv.addObject("bookInfo", viewbook);
        mv.setViewName("jsp/book/bookdetails.jsp");
        return mv;
    }

    @RequestMapping("/addToCart.do")
    @ResponseBody
    private String addTOCart(String bookId, HttpServletRequest request) {
        ViewBook book = bookService.findBookById(Integer.parseInt(bookId));
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        if(shopCart==null) {
            shopCart=new Cart();
            request.getSession().setAttribute("shopCart", shopCart);
        }
        shopCart.addBook(book);
        return String.valueOf(shopCart.getTotQuan());
    }

    @RequestMapping("/cartGoodsNumChange.do")
    @ResponseBody
    public Map<String, Object> cartGoodsNumChange(String bookId,String quantity,HttpServletRequest request){
        Integer bookIdInt = 0;
        if (bookId != null) {
            bookIdInt = Integer.parseInt(bookId);
        }
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        System.out.println(shopCart);
        CartItem item = shopCart.getMap().get(bookIdInt);
        System.out.println(item);
        item.setQuantity(Integer.parseInt(quantity));
        Map<String, Object> map = new HashMap<>();
        map.put("subtotal", item.getSubtotal());
        map.put("totPrice", shopCart.getTotPrice());
        map.put("totQuan", shopCart.getTotQuan());
        map.put("quantity", item.getQuantity());
        return map;
    }

    @RequestMapping("/cartGoodsDel.do")
    public String cartGoodsDel(String bookId,HttpServletRequest request) {
        Integer bookIdInt=Integer.parseInt(bookId);
        Cart shopCart = (Cart) request.getSession().getAttribute("shopCart");
        if(shopCart.getMap().containsKey(bookIdInt)) {
            shopCart.getMap().remove(bookIdInt);
        }
        return "redirect:jsp/book/cart.jsp";
    }

    @RequestMapping("/cartGoodsDelAll.do")
    public String cartGoodsDelAll(HttpServletRequest request) {
        request.getSession().removeAttribute("shopCart");
        return "redirect:jsp/book/cart.jsp";
    }

    @RequestMapping("/searchBook.do")
    public ModelAndView searchBook(String bookName) {
        int curPage = 1;
        PageBean pb = null;
        List<ViewBook> bookList = null;
        if(bookName == null || bookName == "") {
            pb = new PageBean(curPage, 12, bookService.bookReadCount());
            bookList = bookService.PageBookList(pb);
        }else {
            pb = new PageBean(curPage, 12, bookService.bookReadCountByBookName(bookName));
            bookList = bookService.bookListByBookName(pb,bookName);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "所有图书");
        mv.addObject("pageBean", pb);
        mv.addObject("bookList", bookList);
        mv.setViewName("jsp/book/booklist.jsp");
        return mv;
    }


    @RequestMapping("submitOrder.do")
    public ModelAndView submitOrder(HttpServletRequest request) {
        //获得及生成一些需要的对象和数据
        HttpSession session = request.getSession();
        Cart cart=(Cart) session.getAttribute("shopCart");
        User user=(User) session.getAttribute("landing");
        String orderNum= RanUtil.getOrderNum();//生成的订单号
        String orderDate= DateUtil.show();//生成订单日期
        Order order=new Order();
        //给订单对象属性赋值
        order.setOrderNum(orderNum);
        order.setOrderDate(orderDate);
        order.setMoney(cart.getTotPrice());
        order.setOrderStatus(1);
        order.setUserId(user.getUserId());

        ModelAndView mv = new ModelAndView();
        if(bookService.orderAdd(order)) {
            //订单保存成功通过订单号获取订单编号，订单项留用
            order.setOrderId(bookService.findOrderIdByOrderNum(orderNum));
            for(Map.Entry<Integer, CartItem> meic:cart.getMap().entrySet()) {
                OrderItem oi=new OrderItem();
                oi.setBookId(meic.getKey());
                oi.setQuantity(meic.getValue().getQuantity());
                oi.setOrderId(order.getOrderId());
                bookService.orderItemAdd(oi);
            }
            //订单项保存结束清空购物车，返回订单提交成功
            session.removeAttribute("shopCart");
            mv.addObject("orderNum", order.getOrderNum());
            mv.addObject("money", order.getMoney());
            mv.setViewName("jsp/book/ordersuccess.jsp");
        }else {
            mv.addObject("suberr", "订单提交失败，请重新提交");
            mv.setViewName("jsp/book/cart.jsp");
        }
        return mv;
    }

    @RequestMapping("/viewOrder.do")
    public ModelAndView viewOrder(String page,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user=(User)request.getSession().getAttribute("landing");
        if(user==null) {
            mv.setViewName("redirect:jsp/book/reg.jsp?type=login");
        }else {
            int curPage = 1;
            if (page != null) {
                curPage = Integer.parseInt(page);
            }
            PageBean pb= new PageBean(curPage, 5,bookService.orderReadCount(user.getUserId()) );
            List<Order> orderList = bookService.viewOrder(pb,user.getUserId());

            for(Order order:orderList) {
                //通过订单编号查询订单项集合
                order.setoItem(bookService.findItemByOrderId(order.getOrderId()));
                for(OrderItem oi:order.getoItem()) {
                    //通过图书id获取图书对象
                    oi.setBook(bookService.OrderFindBookById(oi.getBookId()));
                }
            }
            mv.addObject("pageBean", pb);
            mv.addObject("orderList",orderList);
            mv.setViewName("jsp/book/myorderlist.jsp");
        }
        return mv;
    }

    @RequestMapping("/confirmReceipt.do")
    public ModelAndView orderProcessing(String id,String page,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer i = -1;
        if (id!=null){
            i = Integer.parseInt(id);
        }
        if(orderManageService.orderChangeStatus(i,3)) {
            mv.addObject("orderMessage", "一个订单操作成功");
        }else {
            mv.addObject("orderMessage", "一个订单操作失败");
        }
        mv.setViewName("viewOrder.do");
        return mv;
    }

    @RequestMapping("/userMessage.do")
    public ModelAndView userMessage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user=(User)request.getSession().getAttribute("landing");
        mv.addObject("user",user);
        mv.setViewName("jsp/book/userMessage.jsp");
        return mv;
    }

}
