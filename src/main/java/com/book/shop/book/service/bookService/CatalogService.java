package com.book.shop.book.service.bookService;

import com.book.shop.book.domain.Catalog;

import java.util.List;

/**
 * @auther 传奇后
 * @date 2021/11/30 14:44
 * @veersion 1.0
 */
public interface CatalogService {
    //获取图书分类信息（包括类别图书的数量）
    List<Catalog> getCatalog();
}
