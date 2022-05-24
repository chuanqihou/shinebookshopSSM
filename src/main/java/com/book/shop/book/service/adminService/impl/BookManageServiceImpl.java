package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.BookDao;
import com.book.shop.book.dao.OrderItemDao;
import com.book.shop.book.dao.UpLoadImgDao;
import com.book.shop.book.domain.Book;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.domain.ViewBook;
import com.book.shop.book.service.adminService.BookManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/8 14:32
 * @veersion 1.0
 */
@Service
public class BookManageServiceImpl implements BookManageService {
    @Resource
    private BookDao bookDao;
    @Resource
    private UpLoadImgDao upLoadImgDao;
    @Resource
    private OrderItemDao orderItemDao;

    @Override
    public Book queryBookById(int idInt) {
        return bookDao.queryBookById(idInt);
    }

    @Override
    public boolean deleteBookByBookId(int idInt) {
        orderItemDao.deleteByBookId(idInt);
        int result = bookDao.deleteBookByBookId(idInt);
        return result>0?true:false;
    }

    @Override
    public boolean deleteImgById(int imgId) {
        int result = upLoadImgDao.deleteImgById(imgId);
        return result>0?true:false;
    }

    @Override
    public boolean bookAdd(Book book) {
        int result = bookDao.bookAdd(book);
        return result==1?true:false;
    }

    @Override
    public boolean findBookByBookName(String bookName) {
        int result = bookDao.findBookByBookName(bookName);
        return result==1?true:false;
    }

    @Override
    public ViewBook findViewBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public long bookReadCountByBookName(String bookName) {
        int count = bookDao.bookReadCountByBookName(bookName);
        return count;
    }

    @Override
    public Object bookListByBookName(PageBean pb, String bookName) {
        int i = (pb.getCurPage() - 1) * pb.getMaxSize();
        int j = pb.getMaxSize();
        Map<String,Object> map = new HashMap<>();
        map.put("i", i);
        map.put("j", j);
        map.put("bookName",bookName);
        List<ViewBook> list = bookDao.bookListByBookName(map);
        return list;
    }

    @Override
    public long bookReadCount() {
        return bookDao.bookReadCount();
    }

    @Override
    public Object PageBookList(PageBean pageBean) {
        int i = (pageBean.getCurPage() - 1) * pageBean.getMaxSize();
        int j = pageBean.getMaxSize();
        Map<String, Integer> map = new HashMap<>();
        map.put("i",i);
        map.put("j",j);
        return bookDao.PageBookList(map);
    }

    @Override
    public Book findBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public boolean bookUpdate(Book book) {
        int result = bookDao.bookUpdate(book);
        return result==1?true:false;
    }

    @Override
    public List<ViewBook> findViewsBookById(int bookId) {
        return bookDao.findViewsBookById(bookId);
    }
}
