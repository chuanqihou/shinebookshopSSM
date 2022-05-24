package com.book.shop.book.dao;

import com.book.shop.book.domain.UpLoadImg;

/**
 * @auther 传奇后
 * @date 2021/12/8 14:42
 * @veersion 1.0
 */
public interface UpLoadImgDao {
    int deleteImgById(int imgId);

    int imgAdd(UpLoadImg upLoadImg);

    int findIdByImgName(String imgName);

    int imgUpdate(UpLoadImg upImg);
}
