package com.maddog.articket.commoditypicture.dao;

import com.maddog.articket.commoditypicture.entity.CommodityPicture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品圖片 DAO
 */
@Mapper
public interface CommodityPictureDao {

	/**
	 * 新增
	 *
	 * @param commodityPicture
	 * 			商品圖片
	 * @return 成功筆數
	 */
	int insert(CommodityPicture commodityPicture);

	/**
	 * 依商品圖片 ID 查詢
	 *
	 * @param commodityPictureId
	 * 			商品圖片 ID
	 * @return 商品圖片
	 */
	CommodityPicture findById(Integer commodityPictureId);

	/**
	 * 查詢所有商品圖片
	 *
	 * @return 商品圖片清單
	 */
	List<CommodityPicture> findAll();

	/**
	 * 依商品 ID 查詢第一個商品圖片
	 *
	 * @param commodityId
	 * 			商品 ID
	 * @return 商品圖片
	 */
	CommodityPicture getFirstByCommodityId(Integer commodityId);

}
