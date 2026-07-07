package com.maddog.articket.activity.dao;

import com.maddog.articket.activity.dto.*;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 活動 DAO
 */
@Mapper
public interface ActivityDao {

    /**
     * 新增
     *
     * @param activity
     *          活動 DO
     * @return 成功筆數
     */
    int insert(Activity activity);

    /**
     * 更新
     *
     * @param activityForUpdate
     *          活動更新 DTO
     * @return 成功筆數
     */
    int update(ActivityForUpdate activityForUpdate);

    /**
     * 依 ID 查詢活動 DO
     *
     * @param activityId
     *          活動 ID
     * @return 活動 DO
     */
    Activity findById(Integer activityId);

    /**
     * 依 ID 查詢活動 VO
     *
     * @param activityId
     *          活動 ID
     * @return 活動 VO
     */
    ActivityFrontEndForView findByIdForView(Integer activityId);

    /**
     * 查詢所有活動 DO
     *
     * @return 活動清單
     */
    List<Activity> findAll();

    /**
     * 依條件查詢活動 VO 清單
     *
     * @param activityQueryCondition
     *          活動查詢條件 DTO
     * @return 活動清單
     */
    List<ActivityFrontEndForView> findByConditionForView(ActivityQueryCondition activityQueryCondition);

    /**
     * 確認活動是否由該廠商所有
     *
     * @param activityId
     *          活動 ID
     * @param partnerId
     *          廠商 ID
     * @return 是/否
     */
    boolean isActivityOwnedByPartner(Integer activityId, Integer partnerId);

    /**
     * 依活動 ID 查詢圖片 ID 清單
     *
     * @param activityId
     * 			活動 ID
     * @return 圖片 ID 清單
     */
    List<Integer> findActivityPictureIdByActivityId(Integer activityId);

    /**
     * 依活動 ID 查詢活動時段清單
     *
     * @param activityId
     * 			活動 ID
     * @return 活動時段清單
     */
    List<ActivityTimeSlot> findActivityTimeSlotByActivityId(Integer activityId);

    /**
     * 依廠商 ID 取得活動清單
     *
     * @param partnerId
     * 			廠商 ID
     * @return 活動清單
     */
    List<ActivityDisplayForView> getActivityDisplayForViewByPartnerId(Integer partnerId);

    /**
     * 將該 ID 活動設為已設定票券
     *
     * @param activityId
     * 			活動 ID
     * @return 成功筆數
     */
    int setTicketSetStatusFinished(Integer activityId);

    /**
     * 依廠商 ID 查詢活動清單
     *
     * @param partnerId
     *          廠商 ID
     * @return 活動清單
     */
    List<Activity> findByPartnerMemberId(Integer partnerId);

    /**
     * 查詢 index.html 顯示用 VO 清單
     *
     * @return 活動 VO 清單
     */
    List<ActivityIndexForView> getActivityForIndex();

}
