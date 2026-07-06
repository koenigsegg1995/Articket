package com.maddog.articket.commoditypicture.service.impl;

import com.maddog.articket.commoditypicture.dao.CommodityPictureDao;
import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import com.maddog.articket.commoditypicture.service.pri.CommodityPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品圖片 Service Implementation
 */
@Service("commodityPictureService")
public class CommodityPictureServiceImpl implements CommodityPictureService {

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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
    @Override
    @Transactional(readOnly = true)
    public CommodityPicture getOneCommodityPicture(Integer commodityPictureId) {
        return commodityPictureDao.findById(commodityPictureId);
    }

    /**
     * 查詢所有商品圖片
     *
     * @return 商品圖片清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<CommodityPicture> getAll() {
        return commodityPictureDao.findAll();
    }

}