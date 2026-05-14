package com.maddog.articket.commodity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.dao.CommodityDao;
import com.maddog.articket.commodity.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 商品 Service Implementation
 */
@Service("commodityService")
public class CommodityService {

    /**
     * 商品 DAO
     */
    @Autowired
    private CommodityDao commodityDao;

    /**
     * 活動 DAO
     */
    @Autowired
    private ActivityDao activityDao;

    public void addCommodity(Commodity commodity) {
        commodityDao.save(commodity);
    }

    public void updateCommodity(Commodity commodity) {
        commodityDao.save(commodity);
    }

    public void deleteCommodity(Integer commodityID) {
        if (commodityDao.existsById(commodityID))
            commodityDao.deleteById(commodityID);
    }

    public Commodity getOneCommodity(Integer commodityID) {
        Optional<Commodity> optional = commodityDao.findById(commodityID);
        return optional.orElse(null);

    }

    public List<Commodity> getAll() {
        return commodityDao.findAll();
    }

    public List<Commodity> getCommoditiesByActivity(Integer activityID) {
        return commodityDao.findByActivityId(activityID);
    }

    public List<Activity> getAllActivities() {
        return commodityDao.findAllDistinctActivities();
    }

    public List<Activity> getActivitiesByPartnerMember(Integer partnerMemberID) {
        List<Activity> activitiesFromCommodities = commodityDao.findActivitiesByMemberId(partnerMemberID);
        List<Activity> allActivities = commodityDao.findAllActivitiesByPartnerMemberID(partnerMemberID);

        // 合併兩個列表並去重
        Set<Activity> uniqueActivities = new HashSet<>(activitiesFromCommodities);
        uniqueActivities.addAll(allActivities);

        return new ArrayList<>(uniqueActivities);

//        return repository.findActivitiesByPartnerMemberID(partnerMemberID);
    }

    public boolean isActivityOwnedByPartner(Integer activityID, Integer partnerID) {
        return activityDao.isActivityOwnedByPartner(activityID, partnerID);
    }

    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityID, Pageable pageable) {
        return commodityDao.findByActivityId(activityID, pageable);
    }

}