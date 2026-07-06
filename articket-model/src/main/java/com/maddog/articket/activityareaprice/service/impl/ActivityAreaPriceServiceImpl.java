package com.maddog.articket.activityareaprice.service.impl;

import com.maddog.articket.activityareaprice.dao.ActivityAreaPriceDao;
import com.maddog.articket.activityareaprice.entity.ActivityAreaPrice;
import com.maddog.articket.activityareaprice.service.pri.ActivityAreaPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 活動區域價格 Service Implementation
 */
@Service("activityAreaPriceService")
public class ActivityAreaPriceServiceImpl implements ActivityAreaPriceService {

    /**
     * 活動區域價格 DAO
     */
    @Autowired
    private ActivityAreaPriceDao activityAreaPriceDao;

    /**
     * 依活動區域價格 ID 查詢活動區域價格
     *
     * @param activityAreaPriceId
     *          活動區域價格 ID
     * @return 活動區域價格
     */
    @Override
    @Transactional(readOnly = true)
    public ActivityAreaPrice findById(Integer activityAreaPriceId){
        return activityAreaPriceDao.findById(activityAreaPriceId);
    }

    /**
     * 依場館區域 ID 與活動 ID 查詢活動區域價格
     *
     * @param venueAreaId
     * 			場館區域 ID
     * @param activityId
     * 			活動 ID
     * @return 活動區域價格
     */
    @Override
    @Transactional(readOnly = true)
    public ActivityAreaPrice findByVenueAreaIdAndActivityId(Integer venueAreaId, Integer activityId) {
        return activityAreaPriceDao.findByVenueAreaIdAndActivityId(venueAreaId, activityId);
    }

    /**
     * 更新或創建活動區域價格
     *
     * @param activityAreaPrice
     *         活動區域價格
     * @return 成功筆數
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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