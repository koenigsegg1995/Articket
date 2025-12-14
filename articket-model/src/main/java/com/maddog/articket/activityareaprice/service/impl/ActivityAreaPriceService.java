package com.maddog.articket.activityareaprice.service.impl;

import com.maddog.articket.activityareaprice.dao.ActivityAreaPriceRepository;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ActivityAreaPriceService {
    @Autowired
    private ActivityAreaPriceRepository activityAreaPriceRepository;
    
    // 用場館區域ID跟活動ID來查找活動區域價格ID
    public ActivityAreaPrice findActivityAreaPrice(Integer venueAreaID, Integer activityID) {
        return activityAreaPriceRepository.findActivityAreaPrice(venueAreaID, activityID);
    }

    /**
     * 更新或創建活動區域價格
     * @param venueAreaID 場地區域ID
     * @param activityID 活動ID
     * @param newPrice 新價格
     * @return 更新或創建後的ActivityAreaPrice對象
     */
    @Transactional
    public ActivityAreaPrice updateOrCreateActivityAreaPrice(Integer venueAreaID, Integer activityID, BigDecimal newPrice) {
        int updatedRows = activityAreaPriceRepository.updateActivityAreaPrice(venueAreaID, activityID, newPrice);
        
        if (updatedRows > 0) {
            // 如果更新成功，返回更新後的對象
            return findActivityAreaPrice(venueAreaID, activityID);
        } else {
            // 如果沒有更新任何行，創建新記錄
            ActivityAreaPrice newAreaPrice = new ActivityAreaPrice();
            // 這裡可能需要設置VenueArea和Activity，取決於您的需求
            // newAreaPrice.setVenueArea(/* 獲取VenueArea對象 */);
            // newAreaPrice.setActivity(/* 獲取Activity對象 */);
            newAreaPrice.setActivityAreaPrice(newPrice);
            return activityAreaPriceRepository.save(newAreaPrice);
        }
    }
}