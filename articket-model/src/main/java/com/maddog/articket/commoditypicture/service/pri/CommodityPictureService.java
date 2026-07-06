package com.maddog.articket.commoditypicture.service.pri;

import com.maddog.articket.commoditypicture.entity.CommodityPicture;

import java.util.List;

/**
 * 商品圖片 Service Interface
 */
public interface CommodityPictureService {

    /**
     * 新增
     *
     * @param commodityPicture
     *          商品圖片
     * @return 成功筆數
     */
    int addCommodityPicture(CommodityPicture commodityPicture);

    /**
     * 依商品圖片 ID 查詢
     *
     * @param commodityPictureId
     * 			商品圖片 ID
     * @return 商品圖片
     */
    CommodityPicture getOneCommodityPicture(Integer commodityPictureId);

    /**
     * 查詢所有商品圖片
     *
     * @return 商品圖片清單
     */
    List<CommodityPicture> getAll();

}