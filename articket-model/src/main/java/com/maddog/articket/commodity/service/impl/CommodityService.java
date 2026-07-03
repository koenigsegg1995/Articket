package com.maddog.articket.commodity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.dao.CommodityDao;
import com.maddog.articket.commodity.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public int addCommodity(Commodity commodity) {
        return commodityDao.insert(commodity);
    }

    public int updateCommodity(Commodity commodity) {
        return commodityDao.update(commodity);
    }

    public int deleteCommodity(Integer commodityId) {
        return commodityDao.deleteById(commodityId);
    }

    public Commodity getOneCommodity(Integer commodityId) {
        return commodityDao.findById(commodityId);
    }

    public List<Commodity> getAll() {
        return commodityDao.findAll();
    }

    public List<Commodity> getCommoditiesByActivity(Integer activityId) {
        return commodityDao.findByActivityId(activityId);
    }

    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityId, Pageable pageable) {
        // 查詢分頁結果
        List<Commodity> result = commodityDao.findByActivityIdPaginated(activityId, (int) pageable.getOffset(), pageable.getPageSize());

        // 總筆數
        int total = commodityDao.countByActivityId(activityId);

        return new PageImpl<>(result, pageable, total);
    }

    public List<Activity> getAllActivities() {
        return commodityDao.findAllDistinctActivities();
    }

    public List<Activity> getActivitiesByPartnerMember(Integer partnerMemberId) {
        List<Activity> activitiesFromCommodities = commodityDao.findActivitiesByMemberId(partnerMemberId);
        List<Activity> allActivities = activityDao.findByPartnerMemberId(partnerMemberId);

        // 合併兩個列表並去重
        Set<Activity> uniqueActivities = new HashSet<>(activitiesFromCommodities);
        uniqueActivities.addAll(allActivities);

        return new ArrayList<>(uniqueActivities);

//        return repository.findActivitiesByPartnerMemberID(partnerMemberID);
    }

    public boolean isActivityOwnedByPartner(Integer activityId, Integer partnerId) {
        return activityDao.isActivityOwnedByPartner(activityId, partnerId);
    }

}