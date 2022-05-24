package com.book.shop.book.controller.adminController;

import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.adminService.CatalogManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/29 20:41
 * @veersion 1.0
 */
@Controller
@RequestMapping("/jsp/admin")
public class CatalogManageController {
    @Resource
    private CatalogManagerService catalogManagerService;

    @RequestMapping("/queryAllCatalog.do")
    public ModelAndView queryAllCatalog(String page) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 8;
        PageBean pb = new PageBean(curPage, maxSize, catalogManagerService.catalogAllCount());
        mv.addObject("pageBean", pb);
        mv.addObject("catalogList", catalogManagerService.queryAllCatalog(pb));
        mv.setViewName("bookManage/catalogList.jsp");
        return mv;
    }

    @RequestMapping("/CatalogAdd.do")
    public ModelAndView catalogAdd(String catalogName) {
        ModelAndView mv = new ModelAndView();
        if(catalogManagerService.catalogAdd(catalogName)) {
            mv.addObject("catalogMessage", "增加分类成功");
            mv.setViewName("queryAllCatalog.do");
        }else {
            mv.addObject("catalogMessage", "增加分类失败");
            mv.setViewName("bookManage/catalogAdd.jsp");
        }
        return mv;
    }

    @RequestMapping("/CatalogFindByName.do")
    @ResponseBody
    public Map<String, String> CatalogFindByName(String param) {
        Map<String, String> map = new HashMap();
        if (catalogManagerService.findCatalogByCatalogName(param)) {
            map.put("info", "该分类已存在");
            map.put("status", "n");
        } else {
            map.put("info", "输入正确");
            map.put("status", "y");
        }
        return map;
    }

    @RequestMapping("/catalogDelete.do")
    public ModelAndView catalogDelete(String id) {
        ModelAndView mv = new ModelAndView();
        if(catalogManagerService.catalogDelete(id)) {
            mv.addObject("catalogMessage", "该分类已删除（包括该分类下的所有图书已删除）");
        }else {
            mv.addObject("catalogMessage", "该分类删除失败");
        }
        mv.setViewName("queryAllCatalog.do");
        return mv;
    }

    @RequestMapping("/catalogDeletes.do")
    public ModelAndView catalogDeletes(String[] ids) {
        ModelAndView mv = new ModelAndView();
        if(catalogManagerService.catalogDeletes(ids)) {
            mv.addObject("catalogMessage", "分类已批量删除（包括分类下的所有图书已删除）");
        }else {
            mv.addObject("catalogMessage", "分类删除失败");
        }
        //用户删除成功失败都跳转到用户列表页面
        mv.setViewName("queryAllCatalog.do");
        return mv;
    }
}
