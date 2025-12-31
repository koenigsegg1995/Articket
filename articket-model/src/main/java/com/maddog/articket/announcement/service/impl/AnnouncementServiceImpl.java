package com.maddog.articket.announcement.service.impl;

import com.maddog.articket.announcement.dao.AnnouncementDao;
import com.maddog.articket.announcement.entity.Announcement;
import com.maddog.articket.announcement.service.pri.AnnouncementService;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_Announcement3;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 公告 Service Implementation
 */
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

    /**
     * 公告 DAO
     */
    @Autowired
    private AnnouncementDao announcementDao;

    /**
     * 新增
     *
     * @param announcement
     * 			Announcement
     * @return 成功筆數
     * 			int
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addAnnouncement(Announcement announcement) {
        return announcementDao.insert(announcement);
    }

    /**
     * 更新
     *
     * @param announcement
     * 			Announcement
     * @return 成功筆數
     * 			int
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateAnnouncement(Announcement announcement) {
        return announcementDao.update(announcement);
    }

    /**
     * 刪除
     *
     * @param announcementId
     * 			Integer
     * @return 成功筆數
     * 			int
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteAnnouncement(Integer announcementId) {
        return announcementDao.deleteById(announcementId);
    }

    /**
     * 依 ID 查詢
     *
     * @param announcementId
     * 			Integer
     * @return 公告
     * 			Announcement
     */
    @Transactional(readOnly = true)
    public Announcement getOneAnnouncement(Integer announcementId) {
        return announcementDao.findById(announcementId);
    }

    /**
     * 查詢所有公告，按創建時間降序排序
     *
     * @return 公告清單
     * 			List<Announcement>
     */
    @Transactional(readOnly = true)
    public List<Announcement> getAll() {
        return announcementDao.findAll();
    }

    /**
     * 分頁查詢，按創建時間降序排序
     *
     * @param pageable
     * 			Pageable
     * @return 公告清單分頁結果
     * 			Page<Announcement>
     */
    @Transactional(readOnly = true)
    public Page<Announcement> getAllPaginated(Pageable pageable) {
        // 查詢分頁結果
        List<Announcement> result = announcementDao.findAllPaginated((int)pageable.getOffset(), pageable.getPageSize());

        // 總筆數
        int total = announcementDao.countAll();

        return new PageImpl<>(result, pageable, total);
    }

}
