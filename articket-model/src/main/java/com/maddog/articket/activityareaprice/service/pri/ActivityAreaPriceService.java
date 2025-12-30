package com.maddog.articket.activityareaprice.service.pri;

import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;

/**
 * 活動區域價格 Service Interface
 */
public interface ActivityAreaPriceService {

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
     * 更新或創建活動區域價格
     *
     * @param activityAreaPrice
     *         ActivityAreaPrice
     * @return 成功筆數
     *          int
     */
    int updateOrCreateActivityAreaPrice(ActivityAreaPrice activityAreaPrice);

}