package com.book.shop.book.service.adminService;

import com.book.shop.book.domain.Catalog;
import com.book.shop.book.domain.PageBean;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/12/27 16:42
 * @veersion 1.0
 */
public interface CatalogManagerService {
    List<Catalog> queryCatalog();

    long catalogAllCount();

    List<Catalog> queryAllCatalog(PageBean pb);

    boolean catalogAdd(String catalogName);

    boolean findCatalogByCatalogName(String param);

    boolean catalogDelete(String id);

    boolean catalogDeletes(String[] ids);
}
