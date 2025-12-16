package com.maddog.articket.activity.dao;

import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityDao {

    /**
     * 新增活動
     *
     * @param activity
     *          Activity
     * @return 成功筆數
     *          int
     */
    int insert(Activity activity);

    /**
     * 更新活動
     *
     * @param activity
     *          Activity
     * @return 成功筆數
     *          int
     */
    // TODO:
    int update(Activity activity);

    /**
     * 刪除活動
     *
     * @param activityId
     *          Integer
     * @return 成功筆數
     *          int
     */
    int deleteById(Integer activityId);

    /**
     * 依 ID 查詢活動 DO
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
    Activity findById(Integer activityId);

    /**
     * 依 ID 查詢活動 VO
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          ActivityForView
     */
    ActivityForView findByIdForView(Integer activityId);

    /**
     * 查詢所有活動 DO
     *
     * @return 活動清單
     *          List<Activity>
     */
    List<Activity> findAll();

    /**
     * 依條件查詢活動 VO 清單
     *
     * @param activityQueryCondition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
     */
    List<ActivityForView> findByConditionForView(ActivityQueryCondition activityQueryCondition);

    /**
     * 確認活動是否由該廠商所有
     *
     * @param activityId
     *          Integer
     * @param partnerId
     *          Integer
     * @return 是/否
     *          boolean
     */
    boolean isActivityOwnedByPartner(Integer activityId, Integer partnerId);

    /**
     * 依活動 ID 查詢圖片 ID 清單
     *
     * @param activityId
     * 			Integer
     * @return 圖片 ID 清單
     * 			List<Integer>
     */
    List<Integer> findActivityPictureIdByActivityId(Integer activityId);

    /**
     * 依活動 ID 查詢活動時段清單
     *
     * @param activityId
     * 			Integer
     * @return 活動時段清單
     * 			List<ActivityTimeSlot>
     */
    List<ActivityTimeSlot> findActivityTimeSlotByActivityId(Integer activityId);

}
