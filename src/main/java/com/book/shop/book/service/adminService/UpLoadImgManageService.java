package com.book.shop.book.service.adminService;

import com.book.shop.book.domain.UpLoadImg;

/**
 * @auther 传奇后
 * @date 2021/12/27 14:28
 * @veersion 1.0
 */
public interface UpLoadImgManageService {
    boolean imgAdd(UpLoadImg upLoadImg);

    Integer findIdByImgName(String imgName);

    boolean imgUpdate(UpLoadImg upImg);
}
