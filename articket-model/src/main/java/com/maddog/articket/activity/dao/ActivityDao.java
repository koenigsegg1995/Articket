package com.maddog.articket.activity.dao;

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
     * @param activityID
     *          Integer
     * @return 成功筆數
     *          int
     */
    int deleteById(Integer activityID);

    /**
     * 依 ID 查詢活動
     *
     * @param activityID
     *          Integer
     * @return 活動
     *          Activity
     */
    Activity findById(Integer activityID);

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
     *          List<Activity>
     */
    List<Activity> findByCondition(ActivityQueryCondition activityQueryCondition);

    /**
     * 確認活動是否由該廠商所有
     *
     * @param activityID
     *          Integer
     * @param partnerID
     *          Integer
     * @return 是/否
     *          boolean
     */
    boolean isActivityOwnedByPartner(Integer activityID, Integer partnerID);

}
