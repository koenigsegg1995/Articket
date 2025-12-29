package com.maddog.articket.activity.service.pri;

import com.maddog.articket.activity.dto.*;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;

import java.util.List;
import java.util.Set;

/**
 * 活動 Service Interface
 */
public interface ActivityService {

	/**
	 * 新增
	 *
	 * @param activityForAdd
	 *            ActivityForAdd
	 * @param partnerId
	 *            Integer
	 * @param venueId
	 *            Integer
	 * @param activityPictureList
	 *            List<ActivityPicture>
	 * @return 成功筆數
	 *            Integer
	 */
	int addActivity(ActivityForAdd activityForAdd, Integer partnerId, Integer venueId, List<ActivityPicture> activityPictureList);

	/**
	 * 修改
	 *
	 * @param activityForUpdate
	 *            ActivityForUpdate
	 * @param addActivityPictureList
	 *            List<ActivityPicture>
	 * @param deleteActivityPictureIds
	 *            Set<Integer>
	 * @return 成功筆數
	 *            int
	 */
	int updateActivity(ActivityForUpdate activityForUpdate,
					   List<ActivityPicture> addActivityPictureList,
					   Set<Integer> deleteActivityPictureIds);

    /**
     * 依 ID 查詢活動 (deprecated)
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
	Activity getOneActivity(Integer activityId);

    /**
     * 依 ID 查詢活動 VO
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          ActivityForView
     */
    ActivityFrontEndForView findByIdForView(Integer activityId);

    /**
     * 查詢所有活動
     *
     * @return 活動清單
     *          List<Activity>
     */
	List<Activity> getAll();

	/**
	 * 依條件查詢
     *
     * @param condition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
	 */
	List<ActivityFrontEndForView> findByConditionForView(ActivityQueryCondition condition);

	/**
	 * 依活動 ID 查詢活動圖片 ID 清單
	 *
	 * @param activityId
	 * 			Integer
	 * @return 活動圖片 ID 清單
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

	/**
	 * 依場地申請 ID 取得活動新增容器
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 活動新增容器
	 * 			ActivityForAdd
	 */
	ActivityForAdd getActivityForAddByVenueRentalId(Integer venueRentalId);

	/**
	 * 依廠商 ID 取得活動清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 活動清單
	 * 			List<ActivityDisplayForView>
	 */
	List<ActivityDisplayForView> getActivityDisplayForViewByPartnerId(Integer partnerId);

	/**
	 * 依活動 ID 取得活動修改容器
	 *
	 * @param activityId
	 * 			Integer
	 * @return 活動修改容器
	 * 			ActivityForUpdate
	 */
	ActivityForUpdate getActivityForUpdateByActivityId(Integer activityId);

	/**
	 * 將該 ID 活動設為已設定票券
	 *
	 * @param activityId
	 * 			Integer
	 * @return 成功筆數
	 * 			Integer
	 */
	int setTicketSetStatusFinished(Integer activityId);
	
}
