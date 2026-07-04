package com.maddog.articket.commodity.service.pri;

import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.commodity.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品 Service Interface
 */
public interface CommodityService {

    /**
     * 新增
     *
     * @param commodity
     *          商品
     * @return 成功筆數
     */
    int addCommodity(Commodity commodity);

    /**
     * 更新
     *
     * @param commodity
     *          商品
     * @return 成功筆數
     */
    int updateCommodity(Commodity commodity);

    /**
     * 刪除
     *
     * @param commodityId
     *          商品 ID
     * @return 成功筆數
     */
    int deleteCommodity(Integer commodityId);

    /**
     * 依商品 ID 查詢
     *
     * @param commodityId
     * 			商品 ID
     * @return 商品
     */
    Commodity getOneCommodity(Integer commodityId);

    /**
     * 查詢所有商品
     *
     * @return 商品清單
     */
    List<Commodity> getAll();

    /**
     * 依活動 ID 查詢商品
     *
     * @param activityId
     * 			活動 ID
     * @return 商品清單
     */
    List<Commodity> getCommoditiesByActivity(Integer activityId);

    /**
     * 依活動 ID 查詢商品（分頁）
     *
     * @param activityId
     *          活動 ID
     * @param pageable
     *          分頁資訊
     * @return 分頁結果
     */
    Page<Commodity> getCommoditiesByActivityPaginated(Integer activityId, Pageable pageable);

    /**
     * 查詢所有商品的活動
     *
     * @return 活動清單
     */
    List<Activity> getAllActivities();

    /**
     * 依廠商 ID 查詢所有商品的活動
     *
     * @param partnerMemberId
     *          廠商 ID
     * @return 活動清單
     */
    List<Activity> getActivitiesByPartnerMember(Integer partnerMemberId);

    /**
     * 檢查活動是否由該廠商擁有
     *
     * @param activityId
     *          活動 ID
     * @param partnerId
     *          廠商 ID
     * @return 是/否
     */
    boolean isActivityOwnedByPartner(Integer activityId, Integer partnerId);
}