package com.maddog.articket.announcement.service.pri;

import com.maddog.articket.announcement.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 公告 Service Interface
 */
public interface AnnouncementService {

    /**
     * 新增
     *
     * @param announcement
     * 			Announcement
     * @return 成功筆數
     * 			int
     */
    int addAnnouncement(Announcement announcement);

    /**
     * 更新
     *
     * @param announcement
     * 			Announcement
     * @return 成功筆數
     * 			int
     */
    int updateAnnouncement(Announcement announcement);

    /**
     * 刪除
     *
     * @param announcementId
     * 			Integer
     * @return 成功筆數
     * 			int
     */
    int deleteAnnouncement(Integer announcementId);

    /**
     * 依 ID 查詢
     *
     * @param announcementId
     * 			Integer
     * @return 公告
     * 			Announcement
     */
    Announcement getOneAnnouncement(Integer announcementId);

    /**
     * 查詢所有公告，按創建時間降序排序
     *
     * @return 公告清單
     * 			List<Announcement>
     */
    List<Announcement> getAll();

    /**
     * 分頁查詢，按創建時間降序排序
     *
     * @param pageable
     * 			Pageable
     * @return 公告清單分頁結果
     * 			Page<Announcement>
     */
    Page<Announcement> getAllPaginated(Pageable pageable);

}
