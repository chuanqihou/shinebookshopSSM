package com.book.shop.book.service.adminService.impl;

import com.book.shop.book.dao.UpLoadImgDao;
import com.book.shop.book.domain.UpLoadImg;
import com.book.shop.book.service.adminService.UpLoadImgManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @auther 传奇后
 * @date 2021/12/27 14:29
 * @veersion 1.0
 */
@Service
public class UpLoadImgManageServiceImpl implements UpLoadImgManageService {

    @Resource
    private UpLoadImgDao upLoadImgDao;

    @Override
    public boolean imgAdd(UpLoadImg upLoadImg) {
        int result = upLoadImgDao.imgAdd(upLoadImg);
        return result==1?true:false;
    }

    @Override
    public Integer findIdByImgName(String imgName) {
        int imgId = upLoadImgDao.findIdByImgName(imgName);
        return imgId;
    }

    @Override
    public boolean imgUpdate(UpLoadImg upImg) {
        int result = upLoadImgDao.imgUpdate(upImg);
        return result>0?true:false;
    }
}
