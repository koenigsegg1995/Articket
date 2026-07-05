package com.maddog.articket.commoditypicture.service.impl;

import com.maddog.articket.commoditypicture.dao.CommodityPictureDao;
import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityPictureService")
public class CommodityPictureService {

    /**
     * 商品圖片 DAO
     */
    @Autowired
    private CommodityPictureDao commodityPictureDao;

    /**
     * 新增
     *
     * @param commodityPicture
     *          商品圖片
     * @return 成功筆數
     */
    public int addCommodityPicture(CommodityPicture commodityPicture) {
        return commodityPictureDao.insert(commodityPicture);
    }

    /**
     * 依商品圖片 ID 查詢
     *
     * @param commodityPictureId
     * 			商品圖片 ID
     * @return 商品圖片
     */
    public CommodityPicture getOneCommodityPicture(Integer commodityPictureId) {
        return commodityPictureDao.findById(commodityPictureId);
    }

    /**
     * 查詢所有商品圖片
     *
     * @return 商品圖片清單
     */
    public List<CommodityPicture> getAll() {
        return commodityPictureDao.findAll();
    }

}