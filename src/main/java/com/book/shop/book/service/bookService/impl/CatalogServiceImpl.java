package com.book.shop.book.service.bookService.impl;

import com.book.shop.book.dao.CatalogDao;
import com.book.shop.book.domain.Catalog;
import com.book.shop.book.service.bookService.CatalogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/11/30 14:48
 * @veersion 1.0
 */
@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    private CatalogDao catalogDao;

    /**
     * 获取图书分类信息（包括类别图书的数量）
     * @return
     */
    @Override
    public List<Catalog> getCatalog() {
        List<Catalog> catalogList = catalogDao.getCatalog();
        return catalogList;
    }
}
