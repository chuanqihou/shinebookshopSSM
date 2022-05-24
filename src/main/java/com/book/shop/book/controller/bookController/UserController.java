package com.book.shop.book.controller.bookController;

import com.book.shop.book.domain.User;
import com.book.shop.book.service.bookService.UserService;
import com.book.shop.utill.ImageCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/4 11:15
 * @veersion 1.0
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/loginStatus.do")
    @ResponseBody

    public Map<String,String> loginStatus(HttpServletRequest request) {
        User user=  (User) request.getSession().getAttribute("landing");
        Map<String,String> map = new HashMap<>();
        if(user!=null) {
            map.put("status", "y");
        }else {
            map.put("status", "n");
        }
        return map;
    }

    @RequestMapping("getLoginCode.do")
    public void getLoginCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 设置浏览器不要缓存此图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletOutputStream outputStream = response.getOutputStream();
        String rands= ImageCode.getImageCode(70, 30, outputStream);
        //将生成的随机四个字符保存在session范围checkCode属性
        request.getSession().setAttribute("checkCode", rands);
        outputStream.close();
    }

    @RequestMapping("/validationCode.do")
    @ResponseBody
    public Map<String, String> validationCode(String param,HttpServletRequest request) {
        String code = param;
        Map<String, String> map = new HashMap<>();
        String ck_code=(String) request.getSession().getAttribute("checkCode");
        if (ck_code.equals(code)) {
            map.put("info", "验证码正确");
            map.put("status", "y");
        } else {
            map.put("info", "验证码输入不正确");
            map.put("status", "n");
        }
        return map;
    }

    @RequestMapping("userLogin.do")
    @ResponseBody
    public Map<String,String> userLogin(String userName,String passWord,HttpServletRequest request) {
        User user=new User(userName,passWord);
        User userByShuJuKu=userService.userLogin(user);
        Map<String,String> map = new HashMap<>();
        if(userByShuJuKu!=null) {
            if("y".equals(userByShuJuKu.getEnabled())) {
                request.getSession().setAttribute("landing", userByShuJuKu);
                map.put("status","y" );
            }else {
                map.put("info", "该用户已被禁用，请联系管理员");
            }
        }else {
            map.put("info", "用户名或密码错误，请重新登陆！");
        }
        return map;
    }

    @RequestMapping("/userRegister.do")
    public ModelAndView userRegister(User user) {
        ModelAndView mv = new ModelAndView();
        user.setEnabled("y");
        //添加之前判断用户名是否在库中存在
        if(false){
            mv.addObject("infoList", "用户添加失败！用户名已存在");
            mv.setViewName("jsp/book/reg.jsp?type=reg");
        }else{
            //执行dao层添加操作
            if(userService.userAdd(user)){
                mv.addObject("infoList", "注册成功！请登陆！");
                mv.setViewName("jsp/book/reg.jsp?type=login");
            }else{
                mv.addObject("userMessage", "用户添加失败！");
                mv.setViewName("jsp/book/reg.jsp?type=reg");
            }
        }
        return mv;
    }

    @RequestMapping("/loginOut.do")
    public String loginOut(HttpServletRequest request) {
        User user =  (User) request.getSession().getAttribute("landing");
        if(user!=null) {
            request.getSession().removeAttribute("landing");
        }
        return "redirect:jsp/book/index.jsp";
    }

    @RequestMapping("/userUpdateById.do")
    public ModelAndView userUpdateById(User user) {
        ModelAndView mv = new ModelAndView();
        boolean flag = userService.userUpdate(user);
        if (flag) {
            mv.addObject("message", "用户信息修改成功！");
        } else {
            mv.addObject("message", "用户信息修改失败！");
        }
        mv.setViewName("jsp/book/userMessage.jsp");
        return mv;
    }
}
