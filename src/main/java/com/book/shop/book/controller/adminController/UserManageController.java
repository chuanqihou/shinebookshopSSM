package com.book.shop.book.controller.adminController;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.User;
import com.book.shop.book.service.bookService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/6 23:24
 * @veersion 1.0
 */
@Controller
@RequestMapping("/jsp/admin")
public class UserManageController {
    @Resource
    private UserService userService;

    @RequestMapping("/queryAllUser.do")
    public ModelAndView queryAllUser(String page, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage=1;
        if(page!=null){
            curPage=Integer.parseInt(page);
        }
        //获取xml中设置的每页显示大小参数
        int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pageBean=new PageBean(curPage,maxSize,userService.bookReadCount());
        mv.addObject("userList", userService.queryAllUser(pageBean));
        mv.addObject("pageBean", pageBean);
        mv.setViewName("userManage/userList.jsp");
        return mv;
    }

    @RequestMapping("/userDeleteById.do")
    public ModelAndView userDeleteById(String id) {
        ModelAndView mv = new ModelAndView();
        if(userService.userDeleteById(id)) {
            mv.addObject("userMessage", "用户已删除");
        }else {
            mv.addObject("userMessage", "用户删除失败");
        }
        //用户删除成功失败都跳转到用户列表页面
        mv.setViewName("queryAllUser.do");
        return mv;
    }

    @RequestMapping("/userEditById.do")
    public ModelAndView userEditById(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userInfo",userService.userEditById(Integer.valueOf(id)));//这里回去是User对象
        mv.setViewName("userManage/userEdit.jsp");
        return mv;
    }

    @RequestMapping("/userUpdateById.do")
    public ModelAndView userUpdateById(User user) {
        ModelAndView mv = new ModelAndView();
        if(userService.userUpdateById(user)) {
            mv.addObject("userMessage", "用户更新成功");
            mv.setViewName("queryAllUser.do");
        }else {
            //更新失败跳转到修改页面
            mv.addObject("userMessage", "用户更新失败");
            mv.addObject("userInfo", userService.userEditById(Integer.valueOf(user.getUserId())));
            mv.setViewName("userManage/userEdit.jsp");
        }
        return mv;
    }

    @RequestMapping("/userDetail.do")
    public ModelAndView userDetail(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userInfo",userService.userEditById(Integer.valueOf(id)));//这里回去是User对象
        mv.setViewName("userManage/userDetail.jsp");
        return mv;
    }

    @RequestMapping("/userAdd.do")
    public ModelAndView userAdd(User user) {
        ModelAndView mv = new ModelAndView();
        user.setEnabled("y");//默认添加的用户启用
        //添加之前判断用户名是否在库中存在
        if(userService.findUserByName(user.getUserName())){
            mv.addObject("userMessage", "用户添加失败！用户名已存在");
            mv.setViewName("userManage/userAdd.jsp");
        }else{
            //执行dao层添加操作
            if(userService.userAdd(user)){
                mv.addObject("userMessage", "用户添加成功！");
                mv.setViewName("queryAllUser.do");
            }else{
                mv.addObject("userMessage", "用户添加失败！");
                mv.setViewName("userManage/userAdd.jsp");
            }
        }
        return mv;
    }

    @RequestMapping("/findUserByName.do")
    @ResponseBody
    public Map<String,String> findUserByName(String param) {
        Map<String, String> map = new HashMap<>();
        if(userService.findUserByName(param)){
            map.put("info", "用户名已存在");
            map.put("status", "n");
        }else{
            map.put("info", "用户名可以使用");
            map.put("status", "y");
        }
        return map;
    }

    @RequestMapping("/userDeletesByIds.do")
    public ModelAndView userDeletesByIds(String[] ids) {
        ModelAndView mv = new ModelAndView();
        boolean flag = false;
        for (String id : ids) {
            flag = userService.userDeleteById(id);
        }
        if(flag) {
            mv.addObject("userMessage", "用户已批量删除");
        }else {
            mv.addObject("userMessage", "用户批量删除失败");
        }
        //用户删除成功失败都跳转到用户列表页面
        mv.setViewName("queryAllUser.do");
        return mv;
    }

    @RequestMapping("/searchUserByUserName.do")
    public ModelAndView searchUserByUserName(String page,String username,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage=1;
        if(page!=null){
            curPage=Integer.parseInt(page);
        }
        //获取xml中设置的每页显示大小参数
        int maxSize=Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pageBean=null;
        if(username != null && username != "") {
            pageBean=new PageBean(curPage,maxSize,userService.searchUserByUserNameCount(username));
            mv.addObject("userList", userService.searchUserByUserNameList(pageBean,username));
        }else {
            pageBean=new PageBean(curPage,maxSize,userService.bookReadCount());
            mv.addObject("userList", userService.queryAllUser(pageBean));
        }
        mv.addObject("pageBean", pageBean);
        mv.setViewName("userManage/userList.jsp");
        return mv;
    }
}
