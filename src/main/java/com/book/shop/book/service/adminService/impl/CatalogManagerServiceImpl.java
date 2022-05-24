package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.BookDao;
import com.book.shop.book.dao.CatalogDao;
import com.book.shop.book.dao.OrderDao;
import com.book.shop.book.dao.OrderItemDao;
import com.book.shop.book.domain.Catalog;
import com.book.shop.book.domain.PageBean;
import com.book.shop.book.service.adminService.CatalogManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/27 16:44
 * @veersion 1.0
 */
@Service
public class CatalogManagerServiceImpl implements CatalogManagerService {
    @Resource
    private CatalogDao catalogDao;
    @Resource
    private BookDao bookDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private OrderDao orderDao;

    @Override
    public List<Catalog> queryCatalog() {
        return catalogDao.getCatalog();
    }

    @Override
    public long catalogAllCount() {
        String sql="select count(*) as count from s_catalog";
        int result = catalogDao.catalogAllCount();
        return result;
    }

    @Override
    public List<Catalog> queryAllCatalog(PageBean pb) {
        String sql = "select * from s_catalog limit ?,?";
        Map<String, Integer> map = new HashMap<>();
        map.put("i",(pb.getCurPage() - 1) * pb.getMaxSize());
        map.put("j", pb.getMaxSize());
        List<Catalog> catalogs = catalogDao.queryAllCatalog(map);
        return catalogs;
    }

    @Override
    public boolean catalogAdd(String catalogName) {
        int result = catalogDao.catalogAdd(catalogName);
        return result>0?true:false;
    }

    @Override
    public boolean findCatalogByCatalogName(String catalogName) {
        int result = catalogDao.findCatalogByCatalogName(catalogName);
        return result>0?true:false;
    }

    @Override
    public boolean catalogDelete(String id) {
        List<Integer> bookIds = bookDao.queryBookIdByCatalogId(id);
        for (Integer bookId : bookIds) {
            orderItemDao.deleteByBookId(bookId);
        }

        for (Integer bookId : bookIds) {
            List<Integer> orderIds = orderItemDao.getOrderIdByBookId(String.valueOf(bookId));
            for (Integer orderId : orderIds) {
                orderDao.deleteOrderByOrderId(orderId);
            }
        }
        bookDao.deleteBookByCatalogId(id);
        int result = catalogDao.catalogDelete(id);
        return result>0?true:false;
    }

    @Override
    public boolean catalogDeletes(String[] ids) {
        int result = 0;
        for (String id : ids) {
            bookDao.deleteBookByCatalogId(id);
            result = catalogDao.catalogDelete(id);
        }
        return result>0?true:false;
    }
}
