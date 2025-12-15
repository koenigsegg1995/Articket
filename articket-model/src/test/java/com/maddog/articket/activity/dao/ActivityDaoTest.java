package com.maddog.articket.activity.dao;

import com.maddog.articket.activity.dto.ActivityQueryCondition;
import com.maddog.articket.activity.entity.Activity;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ActivityDaoTest {

    /**************** 共用屬性 ****************/

    @Autowired
    private ActivityDao dao;

    private SimpleDateFormat formatToDay = new SimpleDateFormat("yyyy-MM-dd");

    /************** 準備測試物件 **************/

    private Activity getDataActivity(){
        Activity activity = new Activity();

        try {
            activity.setPartnerId(999999);
            activity.setVenueId(999999);
            activity.setVenueRentalId(999999);
            activity.setActivityName("test");
            activity.setActivityContent("test");
            activity.setActivityPostTime(formatToDay.parse("2025-12-10"));
            activity.setActivityTag("test");
            activity.setActivityStatus(0);
            activity.setTicketSetStatus(0);
            activity.setSellTime(formatToDay.parse("2025-12-16"));
        }catch (ParseException e){
            e.printStackTrace();
        }

        return activity;
    }

    /**************** 開始測試 ****************/

    /**
     * 新增活動
     *
     * param: activity
     *          Activity
     * return: 成功筆數
     *          int
     */
    @Test
    void test01Insert(){
        assertEquals(1, dao.insert(getDataActivity()));
    }

//    /**
//     * 更新活動
//     *
//     * param: activity
//     *          Activity
//     * return: 成功筆數
//     *          int
//     */
//    @Test
//    void test02Update(){
//        Activity activity = getDataActivity();
//        activity.set
//
//        dao.update(getDataActivity());
//    }
//
//    /**
//     * 刪除活動
//     *
//     * @param activityID
//     *          Integer
//     * @return 成功筆數
//     *          int
//     */
//    int deleteById(Integer activityID);
//
//    /**
//     * 依 ID 查詢活動
//     *
//     * @param activityID
//     *          Integer
//     * @return 活動
//     *          Activity
//     */
//    Activity findById(Integer activityID);
//
//    /**
//     * 查詢所有活動
//     *
//     * @return 活動清單
//     *          List<Activity>
//     */
//    List<Activity> findAll();
//
//    /**
//     * 依條件查詢
//     *
//     * @param activityQueryCondition
//     *          ActivityQueryCondition
//     * @return 活動清單
//     *          List<Activity>
//     */
//    List<Activity> findByCondition(ActivityQueryCondition activityQueryCondition);
//
//    /**
//     * 確認活動是否由該廠商所有
//     *
//     * @param activityID
//     *          Integer
//     * @param partnerID
//     *          Integer
//     * @return 是/否
//     *          boolean
//     */
//    boolean isActivityOwnedByPartner(Integer activityID, Integer partnerID);
  
}