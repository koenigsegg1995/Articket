package com.maddog.articket.activity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.dto.*;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activitypicture.entity.ActivityPicture;
import com.maddog.articket.activitypicture.service.pri.ActivityPictureService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.impl.VenueRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private VenueRentalService venueRentalSvc;

	@Autowired
	private ActivityPictureService activityPictureSvc;

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
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addActivity(ActivityForAdd activityForAdd, Integer partnerId, Integer venueId, List<ActivityPicture> activityPictureList) {
		// 包裝為 DO
		Activity activity = new Activity();
		activity.setPartnerId(partnerId);
		activity.setVenueId(venueId);
		activity.setVenueRentalId(activityForAdd.getVenueRentalId());
		activity.setActivityName(activityForAdd.getActivityName());
		activity.setActivityContent(activityForAdd.getActivityContent());
			// activity_create_time 由 DB 預設 CURRENT_TIMESTAMP
			// activity_post_time 由 活動資訊設定 設定
		activity.setActivityTag(activityForAdd.getActivityTag());
			// activity_status 預設 0
			// ticket_set_status 預設 0
			// sell_time 由 活動資訊設定 設定

		// insert 活動
		int insertCount = activityDao.insert(activity);

		if(insertCount == 1) {
			// 取得新增活動 ID
			Integer activityId = activity.getActivityId();

			// insert 活動圖片
			activityPictureList.forEach(activityPicture -> {
				activityPicture.setActivityId(activityId);

				activityPictureSvc.insertActivityPicture(activityPicture);
			});
		}

		return insertCount;
	}

	//修改
	public void updateActivity(Activity activity) {
		// activity.update(activity);
	}

	//刪除
	public void deleteActivity(Integer activityID) {
		// activity.delete(activityID);
	}

    /**
     * 依 ID 查詢活動 (deprecated)
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          Activity
     */
    @Override
    @Transactional(readOnly = true)
	public Activity getOneActivity(Integer activityId) {
		 return activityDao.findById(activityId);
	}

    /**
     * 依 ID 查詢活動 VO
     *
     * @param activityId
     *          Integer
     * @return 活動
     *          ActivityForView
     */
    @Override
    @Transactional(readOnly = true)
    public ActivityFrontEndForView findByIdForView(Integer activityId){
        return activityDao.findByIdForView(activityId);
    }

    /**
     * 查詢所有活動
     *
     * @return 活動清單
     *          List<Activity>
     */
    @Override
    @Transactional(readOnly = true)
	public List<Activity> getAll(){
		return activityDao.findAll();
	}

    /**
     * 依條件查詢
     *
     * @param condition
     *          ActivityQueryCondition
     * @return 活動清單
     *          List<ActivityForView>
     */
	@Override
	@Transactional(readOnly = true)
	public List<ActivityFrontEndForView> findByConditionForView(ActivityQueryCondition condition) {
        // 依條件由 DAO 查詢
        List<ActivityFrontEndForView> viewList = activityDao.findByConditionForView(condition);

		// 設定圖片 ID 清單
		for(ActivityFrontEndForView view : viewList) {
			view.setActivityPictureIds(activityDao.findActivityPictureIdByActivityId(view.getActivityId()));
		}

        return viewList;
	}

	/**
	 * 依活動 ID 查詢活動圖片 ID 清單
	 *
	 * @param activityId
	 * 			Integer
	 * @return 活動圖片 ID 清單
	 * 			List<Integer>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findActivityPictureIdByActivityId(Integer activityId){
		return activityDao.findActivityPictureIdByActivityId(activityId);
	}

    /**
     * 依活動 ID 查詢活動時段清單
     *
     * @param activityId
     * 			Integer
     * @return 活動時段清單
     * 			List<ActivityTimeSlot>
     */
    @Override
    @Transactional(readOnly = true)
    public List<ActivityTimeSlot> findActivityTimeSlotByActivityId(Integer activityId){
        return activityDao.findActivityTimeSlotByActivityId(activityId);
    }

	/**
	 * 依場地申請 ID 取得活動新增容器
	 *
	 * @param venueRentalId
	 * 			Integer
	 * @return 活動新增容器
	 * 			ActivityForAdd
	 */
	@Override
	@Transactional(readOnly = true)
	public ActivityForAdd getActivityForAddByVenueRentalId(Integer venueRentalId){
		VenueRental venueRental = venueRentalSvc.getOneVenueRental(venueRentalId);

		ActivityForAdd activityForAdd = new ActivityForAdd();
		activityForAdd.setVenueRentalId(venueRentalId);
		activityForAdd.setActivityName(venueRental.getActivityName());

		return activityForAdd;
	}

	/**
	 * 依廠商 ID 取得活動清單
	 *
	 * @param partnerId
	 * 			Integer
	 * @return 活動清單
	 * 			List<ActivityDisplayForView>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ActivityDisplayForView> getActivityDisplayForViewByPartnerId(Integer partnerId){
		return activityDao.getActivityDisplayForViewByPartnerId(partnerId);
	}

	/**
	 * 依活動 ID 取得活動修改容器
	 *
	 * @param activityId
	 * 			Integer
	 * @return 活動修改容器
	 * 			ActivityForUpdate
	 */
	public ActivityForUpdate getActivityForUpdateByActivityId(Integer activityId){
		// 依活動 ID 取得活動 DO
		Activity activity = activityDao.findById(activityId);

		// 轉換為 update DTO
		ActivityForUpdate activityForUpdate = new ActivityForUpdate();

	}
	
}
