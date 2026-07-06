package com.maddog.articket.activityareaprice.service.pri;

import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;

/**
 * 活動區域價格 Service Interface
 */
public interface ActivityAreaPriceService {

    /**
     * 依活動區域價格 ID 查詢活動區域價格
     *
     * @param activityAreaPriceId
     *          活動區域價格 ID
     * @return 活動區域價格
     */
    ActivityAreaPrice findById(Integer activityAreaPriceId);

    /**
     * 依場館區域 ID 與活動 ID 查詢活動區域價格
     *
     * @param venueAreaId
     * 			場館區域 ID
     * @param activityId
     * 			活動 ID
     * @return 活動區域價格
     */
    ActivityAreaPrice findByVenueAreaIdAndActivityId(Integer venueAreaId, Integer activityId);

    /**
     * 更新或創建活動區域價格
     *
     * @param activityAreaPrice
     *         活動區域價格
     * @return 成功筆數
     */
    int updateOrCreateActivityAreaPrice(ActivityAreaPrice activityAreaPrice);

}