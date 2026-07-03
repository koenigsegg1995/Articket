package com.maddog.articket.commodity.dao;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品　DAO
 */
@Mapper
public interface CommodityDao {

	/**
	 * 新增
	 *
	 * @param commodity
	 * 			商品
	 * @return 成功筆數
	 */
	int insert(Commodity commodity);

	/**
	 * 刪除
	 *
	 * @param commodityId
	 * 			商品 ID
	 * @return 成功筆數
	 */
	int deleteById(Integer commodityId);

	/**
	 * 更新
	 *
	 * @param commodity
	 * 			商品
	 * @return 成功筆數
	 */
	int update(Commodity commodity);

	/**
	 * 依商品 ID 查詢
	 *
	 * @param commodityId
	 * 			商品 ID
	 * @return 商品
	 */
	Commodity findById(Integer commodityId);

	/**
	 * 查詢所有商品
	 *
	 * @return 商品清單
	 */
	List<Commodity> findAll();

	/**
	 * 依活動 ID 查詢商品
	 *
	 * @param activityId
	 * 			活動 ID
	 * @return 商品清單
	 */
	List<Commodity> findByActivityId(Integer activityId);

	/**
	 * 依活動 ID 查詢商品（分頁）
	 *
	 * @param activityId
	 * 			活動 ID
	 * @param offset
	 * 			起始筆數
	 * @param limit
	 * 			筆數
	 * @return 商品清單
	 */
    List<Commodity> findByActivityIdPaginated(@Param("activityId") Integer activityId,
											  @Param("offset") int offset,
											  @Param("limit") int limit);

	/**
	 * 依活動ID查詢商品總筆數
	 *
	 * @param activityId
	 * 			活動 ID
	 * @return 筆數
	 */
	int countByActivityId(Integer activityId);

	/**
	 * 查詢所有商品的活動
	 *
	 * @return 活動清單
	 */
	List<Activity> findAllDistinctActivities();

	/**
	 * 依廠商 ID 查詢所有商品的活動
	 *
	 * @param partnerId
	 * 			廠商 ID
	 * @return 活動清單
	 */
	List<Activity> findActivitiesByMemberId(Integer partnerId);

}