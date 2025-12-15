package com.maddog.articket.activity.dao;

import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
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
     * 依 ID 查詢活動
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
    Activity findById(Integer activityId);

    /**
     * 查詢所有活動
     *
     * @return 活動清單
     *          List<Activity>
     */
    List<Activity> findAll();

    /**
     * 依條件查詢
     *
     * @param activityQueryCondition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
     */
    List<ActivityForView> findByCondition(ActivityQueryCondition activityQueryCondition);

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

}
