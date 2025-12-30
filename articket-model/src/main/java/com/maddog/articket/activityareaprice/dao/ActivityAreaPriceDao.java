package com.maddog.articket.activityareaprice.dao;

import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Query;

/**
 * 活動區域價格 DAO
 */
@Mapper
public interface ActivityAreaPriceDao {

	/**
	 * 新增
	 *
	 * @param activityAreaPrice
	 * 			ActivityAreaPrice
	 * @return 成功筆數
	 *          int
	 */
	int insert(ActivityAreaPrice activityAreaPrice);

	/**
	 * 依場館區域 ID 與活動 ID 查詢活動區域價格
	 *
	 * @param venueAreaId
	 * 			Integer
	 * @param activityId
	 * 			Integer
	 * @return 活動區域價格
	 * 			ActivityAreaPrice
	 */
	ActivityAreaPrice findByVenueAreaIdAndActivityId(Integer venueAreaId, Integer activityId);

	/**
	 * 更新
	 *
	 * @param activityAreaPrice
	 * 			ActivityAreaPrice
	 * @return 成功筆數
	 *          int
	 */
	int update(ActivityAreaPrice activityAreaPrice);

}
