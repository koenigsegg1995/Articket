package com.maddog.articket.commodity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.dao.CommodityDao;
import com.maddog.articket.commodity.entity.Commodity;
import com.maddog.articket.commodity.service.pri.CommodityService;
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
public class CommodityServiceImpl implements CommodityService {

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

    /**
     * 新增
     *
     * @param commodity
     *          商品
     * @return 成功筆數
     */
    public int addCommodity(Commodity commodity) {
        return commodityDao.insert(commodity);
    }

    /**
     * 更新
     *
     * @param commodity
     *          商品
     * @return 成功筆數
     */
    public int updateCommodity(Commodity commodity) {
        return commodityDao.update(commodity);
    }

    /**
     * 刪除
     *
     * @param commodityId
     *          商品 ID
     * @return 成功筆數
     */
    public int deleteCommodity(Integer commodityId) {
        return commodityDao.deleteById(commodityId);
    }

    /**
     * 依商品 ID 查詢
     *
     * @param commodityId
     * 			商品 ID
     * @return 商品
     */
    public Commodity getOneCommodity(Integer commodityId) {
        return commodityDao.findById(commodityId);
    }

    /**
     * 查詢所有商品
     *
     * @return 商品清單
     */
    public List<Commodity> getAll() {
        return commodityDao.findAll();
    }

    /**
     * 依活動 ID 查詢商品
     *
     * @param activityId
     * 			活動 ID
     * @return 商品清單
     */
    public List<Commodity> getCommoditiesByActivity(Integer activityId) {
        return commodityDao.findByActivityId(activityId);
    }

    /**
     * 依活動 ID 查詢商品（分頁）
     *
     * @param activityId
     *          活動 ID
     * @param pageable
     *          分頁資訊
     * @return 分頁結果
     */
    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityId, Pageable pageable) {
        // 查詢分頁結果
        List<Commodity> result = commodityDao.findByActivityIdPaginated(activityId, (int) pageable.getOffset(), pageable.getPageSize());

        // 總筆數
        int total = commodityDao.countByActivityId(activityId);

        return new PageImpl<>(result, pageable, total);
    }

    /**
     * 查詢所有商品的活動
     *
     * @return 活動清單
     */
    public List<Activity> getAllActivities() {
        return commodityDao.findAllDistinctActivities();
    }

    /**
     * 依廠商 ID 查詢所有商品的活動
     *
     * @param partnerMemberId
     *          廠商 ID
     * @return 活動清單
     */
    public List<Activity> getActivitiesByPartnerMember(Integer partnerMemberId) {
//        List<Activity> activitiesFromCommodities = commodityDao.findActivitiesByMemberId(partnerMemberId);
//        List<Activity> allActivities = activityDao.findByPartnerMemberId(partnerMemberId);
//
//        // 合併兩個列表並去重
//        Set<Activity> uniqueActivities = new HashSet<>(activitiesFromCommodities);
//        uniqueActivities.addAll(allActivities);
//
//        return new ArrayList<>(uniqueActivities);

        return activityDao.findByPartnerMemberId(partnerMemberId);
    }

    /**
     * 檢查活動是否由該廠商擁有
     *
     * @param activityId
     *          活動 ID
     * @param partnerId
     *          廠商 ID
     * @return 是/否
     */
    public boolean isActivityOwnedByPartner(Integer activityId, Integer partnerId) {
        return activityDao.isActivityOwnedByPartner(activityId, partnerId);
    }

}