package com.maddog.articket.activity.service.impl;

import com.maddog.articket.activity.dao.ActivityDao;
import com.maddog.articket.activity.dto.ActivityForAdd;
import com.maddog.articket.activity.dto.ActivityForView;
import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
import com.maddog.articket.activity.service.pri.ActivityService;
import com.maddog.articket.activitytimeslot.entity.ActivityTimeSlot;
import com.maddog.articket.venuerental.entity.VenueRental;
import com.maddog.articket.venuerental.service.impl.VenueRentalService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private VenueRentalService venueRentalSvc;
	
	//新增
	public void addActivity(Activity activity) {
		// activity.insert(activity);
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
    public ActivityForView findByIdForView(Integer activityId){
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
	public List<ActivityForView> findByConditionForView(ActivityQueryCondition condition) {
        // 依條件由 DAO 查詢
        List<ActivityForView> viewList = activityDao.findByConditionForView(condition);

		// 設定圖片 ID 清單
		for(ActivityForView view : viewList) {
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
	
}
