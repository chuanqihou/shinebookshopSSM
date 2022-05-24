package com.book.shop.book.dao;


import com.book.shop.book.domain.Catalog;
import com.book.shop.book.domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 图书分类dao层
 * @author thuih
 *
 */
public interface CatalogDao {
	//获取图书分类信息
	List<Catalog> getCatalog();

	int catalogAllCount();

	List<Catalog> queryAllCatalog(Map<String, Integer> map);

    int catalogAdd(String catalogName);

	int findCatalogByCatalogName(String catalogName);

	int catalogDelete(String id);
}
