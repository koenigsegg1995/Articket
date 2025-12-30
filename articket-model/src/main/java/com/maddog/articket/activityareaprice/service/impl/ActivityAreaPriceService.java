package com.maddog.articket.activityareaprice.service.impl;

import com.maddog.articket.activityareaprice.dao.ActivityAreaPriceDao;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityAreaPriceService {

    /**
     * 活動區域價格 DAO
     */
    @Autowired
    private ActivityAreaPriceDao activityAreaPriceDao;

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
    @Transactional(readOnly = true)
    public ActivityAreaPrice findByVenueAreaIdAndActivityId(Integer venueAreaId, Integer activityId) {
        return activityAreaPriceDao.findByVenueAreaIdAndActivityId(venueAreaId, activityId);
    }

    /**
     * 更新或創建活動區域價格
     *
     * @param activityAreaPrice
     *         ActivityAreaPrice
     * @return 成功筆數
     *          int
     */
    @Transactional
    public int updateOrCreateActivityAreaPrice(ActivityAreaPrice activityAreaPrice) {
        int updatedRows = activityAreaPriceDao.update(activityAreaPrice);
        
        if (updatedRows > 0) {
            // 如果更新成功
            return updatedRows;
        } else {
            // 如果沒有更新任何行，創建新記錄
            return activityAreaPriceDao.insert(activityAreaPrice);
        }
    }

}