package com.book.shop.book.controller.adminController;

import com.book.shop.book.domain.*;
import com.book.shop.book.service.adminService.BookManageService;
import com.book.shop.book.service.adminService.CatalogManagerService;
import com.book.shop.book.service.adminService.UpLoadImgManageService;
import com.book.shop.book.service.bookService.BookService;
import com.book.shop.utill.RanUtil;
import com.sun.org.apache.xml.internal.resolver.CatalogManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther 传奇后
 * @date 2021/12/8 13:59
 * @veersion 1.0
 */
@Controller
@RequestMapping("/jsp/admin")
public class BookManageController {
    @Resource
    private BookService bookService;
    @Resource
    private BookManageService bookManageService;
    @Resource
    private UpLoadImgManageService upLoadImgManageService;
    @Resource
    private CatalogManagerService catalogManagerService;

    @RequestMapping("/queryAllBook.do")
    public ModelAndView queryAllBook(String page, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
        PageBean pb = new PageBean(curPage, maxSize, bookService.bookReadCount());
        mv.addObject("pageBean", pb);
        mv.addObject("bookList", bookService.PageBookList(pb));
        mv.setViewName("bookManage/bookList.jsp");
        return mv;
    }

    @RequestMapping("/bookAdd.do")
    public ModelAndView bookAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        String BOOKIMGDIR_PATH = "images/book/bookimg/";
        String PATH = "C:/Users/chuanqihou/IdeaProjects/school/shinebookshopSSM/shinebookshopSSM/src/main/webapp/images/book/bookimg/";
        boolean flag = false;
        Map<String, String> map = new HashMap<>();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        InputStream inputStreamYP = null;
        OutputStream outputStreamYP = null;

        //图书图片保存路径：工程目录/images/book/bookimg/ 下
        File dirPath = new File(request.getServletContext().getRealPath("/") + BOOKIMGDIR_PATH);
        //判断该路径是否存在
        if (!dirPath.exists()) {
            //如果不存在则创建该目录（路径）
            dirPath.mkdirs();
        }
        //获取磁盘文件项目工厂对象
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        //高水平的API文件上传处理（传入磁盘文件项目工厂对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
        // 解决乱码
        servletFileUpload.setHeaderEncoding("ISO8859_1");

        List<FileItem> parseRequest = null;
        try {
            //可以上传多个文件
            parseRequest = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //遍历表单提交的文件
        Iterator<FileItem> iterator = parseRequest.iterator();
        while (iterator.hasNext()) {
            //取出一个文件
            FileItem fileItem = iterator.next();
            // 判断是否是表单的普通字段 true为普通表单字段，false为上传文件内容
            if (fileItem.isFormField()) {
                //获取普通字段 name属性
                //String name = fileItem.getFieldName();
                String name = new String(fileItem.getFieldName().getBytes("ISO8859_1"), "utf-8");
                //获取 name属性对应的value（即用户输入字段）
                //String value = fileItem.getString();
                String value = new String(fileItem.getString().getBytes("ISO8859_1"), "utf-8");
                //将获取到的name和value放入map集合中
                map.put(name, value);
            } else {
                String imgName = null;
                //获取上传文件（图片）类型
                String contentType = fileItem.getContentType();
                //根据类型得到图片文件名（使用UUID随机码）
                if ("image/jpeg".equals(contentType)) {
                    imgName = RanUtil.getUUID() + ".jpg";
                    flag = true;
                }
                if ("image/png".equals(contentType)) {
                    imgName = RanUtil.getUUID() + ".png";
                    flag = true;
                }
                //如果图片名称获取成功
                if (flag) {
                    //获取输入流
                    inputStream = fileItem.getInputStream();
                    //图片保存完整路径
                    File file = new File(dirPath, imgName);
                    //获取输出流（输出路径：图片保存完整路径）
                    outputStream = new FileOutputStream(file);
                    // 保存img信息到map集合中，后面传入对象使用
                    map.put("imgName", imgName);
                    map.put("imgSrc", BOOKIMGDIR_PATH + imgName);
                    map.put("imgType", contentType);

                    File path = new File(PATH,imgName);
                    outputStreamYP = new FileOutputStream(path);
                    inputStreamYP =  new FileInputStream(file);
                }
            }
        }
        // 如果上传的内容小于3个必填项或者图片没有或类型不正确返回
        if (map.size() < 3 || !flag) {
            mv.addObject("bookMessage", "图书添加失败");
            mv.setViewName("queryCatalog.do");
        } else {
            // 验证通过才可以保存图片流到本地
            //读取用户上传图片（输入）并拷贝写（输出）到新的目录（服务器缓存中）
            IOUtils.copy(inputStream, outputStream);
            //读取用户上传图片（输入）并拷贝写（输出）到新的目录（硬盘中）
            IOUtils.copy(inputStreamYP, outputStreamYP);
            //关闭输入和输出流
            outputStream.close();
            inputStream.close();
            outputStreamYP.close();

            // 把map集合中存储的表单数据提取出来转换为book对象
            // 这里要求图书增加的字段要和数据库字段一致，不然map集合转对象会出错
            Book book = new Book();
            book.setBookName(map.get("bookName"));
            book.setPrice(Double.parseDouble(map.get("price")));
            book.setDescription(map.get("desc"));
            book.setAuthor(map.get("author"));
            book.setPress(map.get("press"));
            Date date = new Date();
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            book.setAddTime(date);
            // 图书分类信息
            Catalog catalog = book.getCatalog();
            catalog.setCatalogId(Integer.parseInt(map.get("catalog")));
            // 图片信息
            UpLoadImg upLoadImg = book.getUpLoadImg();
            upLoadImg.setImgName(map.get("imgName"));
            upLoadImg.setImgSrc(map.get("imgSrc"));
            upLoadImg.setImgType(map.get("imgType"));

            // 增加图书先增加图书图片,图书图片增加成功了在添加图书信息
            if (upLoadImgManageService.imgAdd(book.getUpLoadImg())) {
                // 获取图书图片添加后的id
                Integer imgId = upLoadImgManageService.findIdByImgName(upLoadImg.getImgName());
                upLoadImg.setImgId(imgId);

                if (bookManageService.bookAdd(book)) {
                    mv.addObject("bookMessage", "图书添加成功");
                    mv.setViewName("queryAllBook.do");
                } else {
                    mv.addObject("bookMessage", "图书添加失败");
                    mv.setViewName("queryCatalog.do");
                }
            } else {
                // 图片添加失败就判定图书添加失败
                request.setAttribute("bookMessage", "图书添加失败");
                mv.setViewName("queryCatalog.do");
            }
        }
        return mv;
    }

    @RequestMapping("/bookNameIsExist.do")
    @ResponseBody
    public Map<String, String> bookNameIsExist(String param){
        String bookName = param;
        Map<String, String> map = new HashMap<>();
        if (bookManageService.findBookByBookName(bookName)) {
            map.put("info", "该图书已存在");
            map.put("status", "n");
        } else {
            map.put("info", "输入正确");
            map.put("status", "y");
        }
        return map;
    }

    @RequestMapping("/queryCatalog.do")
    public ModelAndView queryCatalog(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("catalog", catalogManagerService.queryCatalog());
        mv.setViewName("bookManage/bookAdd.jsp");
        return mv;
    }


    @RequestMapping("/deleteBookById.do")
    public ModelAndView deleteBookById(String id,HttpServletRequest request) {
        return deleteBookByBookId(request,id);
    }

    @RequestMapping("/bookDeletesById.do")
    public ModelAndView bookDeletes(String ids[],HttpServletRequest request){
        int count = ids.length;
        ModelAndView mv = new ModelAndView();
        List<ModelAndView> viewList = new ArrayList<>();
        for (String id : ids) {
            viewList.add(deleteBookByBookId(request,id));
        }
        if (count == viewList.size()) {
            mv.addObject("bookMessage", "图书已删除");
        }else {
            mv.addObject("bookMessage", "图书删除失败");
        }
        mv.setViewName("queryAllBook.do");
        return mv;
    }

    public ModelAndView deleteBookByBookId(HttpServletRequest request,String id){
        ModelAndView mv = new ModelAndView();
        int idInt=Integer.parseInt(id);
        File contextPath=new File(request.getServletContext().getRealPath("/"));
        Book book=bookManageService.queryBookById(idInt);
        ViewBook viewBook=bookService.findBookById(idInt);
        //这里先删除数据库图书信息，再删除图书图片及本地硬盘图片信息
        if(bookManageService.deleteBookByBookId(idInt)) {
            mv.addObject("bookMessage", "图书已删除");
            if(bookManageService.deleteImgById(book.getImgId())) {
                //删除本地文件
                File f=new File(contextPath,viewBook.getImgSrc());
                if(f.exists()) {
                    f.delete();
                }
                String pa = "C:/Users/chuanqihou/IdeaProjects/school/shinebookshopSSM/shinebookshopSSM/src/main/webapp/";
                File f2=new File(pa,viewBook.getImgSrc());
                if(f2.exists()) {
                    f2.delete();
                }
            }
        }else {
            mv.addObject("bookMessage", "图书删除失败");
        }
        //用户删除成功失败都跳转到用户列表页面
        mv.setViewName("queryAllBook.do");
        return mv;
    }

    @RequestMapping("/bookDetail.do")
    public ModelAndView bookDetail(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("bookInfo", bookManageService.findViewBookById(Integer.parseInt(id)));
        mv.setViewName("bookManage/bookDetail.jsp");
        return mv;
    }

    @RequestMapping("/findBookByName.do")
    public ModelAndView findBookByName(String page,String bookname) {
        ModelAndView mv = new ModelAndView();
        int curPage = 1;
        if (page != null) {
            curPage = Integer.parseInt(page);
        }
        int maxSize = 8;
        PageBean pb = null;
        if(bookname != null && bookname != "") {
            pb = new PageBean(curPage, maxSize, bookManageService.bookReadCountByBookName(bookname));
            mv.addObject("bookList", bookManageService.bookListByBookName(pb,bookname));
        }else {
            pb = new PageBean(curPage, maxSize, bookManageService.bookReadCount());
            mv.addObject("bookList", bookManageService.PageBookList(pb));
        }
        mv.addObject("pageBean", pb);
        mv.setViewName("bookManage/bookList.jsp");
        return mv;
    }

    @RequestMapping("/bookEditById.do")
    public ModelAndView bookEditById(String id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("catalog", catalogManagerService.queryCatalog());//获取图书分类信息
        mv.addObject("bookInfo", bookManageService.findViewBookById(id==null?0:Integer.parseInt(id)));//获取图书信息byId
        mv.setViewName("bookManage/bookEdit.jsp");
        return mv;
    }

    @RequestMapping("/updateBookImage.do")
    public ModelAndView updateBookImage(String id,HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        boolean flag = false;
        String imgSrc = null;
        OutputStream outputStream = null;
        OutputStream outputStreamYP = null;
        InputStream inputStream = null;
        InputStream inputStreamYP = null;
        String imgName = null;
        String contentType = null;

        File contextPath=new File(request.getServletContext().getRealPath("/"));
        String BOOKIMGDIR_PATH = "images/book/bookimg/";
        File dirPath = new File( contextPath,BOOKIMGDIR_PATH);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
        List<FileItem> parseRequest = null;
        try {
            parseRequest = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator<FileItem> iterator = parseRequest.iterator();
        while (iterator.hasNext()) {
            FileItem fileItem = iterator.next();
            if (!fileItem.isFormField()) {
                inputStream = fileItem.getInputStream();
                contentType = fileItem.getContentType();
                if ("image/jpeg".equals(contentType)) {
                    imgName = RanUtil.getUUID() + ".jpg";
                    flag = true;
                }
                if ("image/png".equals(contentType)) {
                    imgName = RanUtil.getUUID() + ".png";
                    flag = true;
                }
            }
        }
        if (flag) {
            imgSrc = BOOKIMGDIR_PATH + imgName;
            outputStream = new FileOutputStream(new File(contextPath,imgSrc));
            inputStreamYP = new FileInputStream(new File(contextPath,imgSrc));
            String pa = "C:/Users/chuanqihou/IdeaProjects/school/shinebookshopSSM/shinebookshopSSM/src/main/webapp/";
            File pathYP = new File(pa,imgSrc);
            outputStreamYP = new FileOutputStream(pathYP);
            IOUtils.copy(inputStream, outputStream);
            IOUtils.copy(inputStreamYP, outputStreamYP);
            outputStream.close();
            inputStream.close();
            //根据图书id去查询图片信息
            ViewBook book = bookManageService.findViewBookById(id==null?0:Integer.parseInt(id));
            UpLoadImg upImg = new UpLoadImg();
            Book book2 = bookManageService.findBookById(id==null?0:Integer.parseInt(id));
            upImg.setImgId(book2.getImgId());
            // 删除旧图片文件如果存在
            File oldImg = new File(contextPath,book.getImgSrc());
            if (oldImg.exists()) {
                oldImg.delete();
            }
            File oldImgYP= new File(pa,book.getImgSrc());
            if (oldImgYP.exists()) {
                oldImgYP.delete();
            }

            upImg.setImgName(imgName);
            upImg.setImgSrc(imgSrc);
            upImg.setImgType(contentType);
            if (upLoadImgManageService.imgUpdate(upImg)) {
                mv.addObject("bookMessage", "图片修改成功");
            } else {
                mv.addObject("bookMessage", "图片修改失败");
            }
        } else {
            mv.addObject("bookMessage", "图片修改失败");
        }
        mv.setViewName("bookEditById.do");
        return mv;
    }

    @RequestMapping("/updateBook.do")
    public ModelAndView updateBook(Book book) {
        ModelAndView mv = new ModelAndView();
        if(bookManageService.bookUpdate(book)) {
            mv.addObject("bookMessage", "修改成功");
            mv.setViewName("queryAllBook.do");
        }else {
            mv.addObject("bookMessage", "修改失败");
            mv.addObject("bookInfo", bookManageService.findBookById(book.getBookId()));
            mv.setViewName("bookManage/bookEdit.jsp");
        }
        return mv;
    }

}
