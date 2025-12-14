package com.maddog.articket.announcement.model.service.impl;

import com.maddog.articket.announcement.model.dao.AnnouncementRepository;
import com.maddog.articket.announcement.model.entity.Announcement;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_Announcement3;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("announcementService")
public class AnnouncementService {

    @Autowired
    AnnouncementRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    // 創建新公告
    public void addAnnouncement(Announcement announcement) {
        repository.save(announcement);
    }

    // 更新現有公告
    public void updateAnnouncement(Announcement announcement) {
        repository.save(announcement);
    }

    // 刪除公告
    public void deleteAnnouncement(Integer announcementID) {
        if (repository.existsById(announcementID))
            repository.deleteByAnnouncementID(announcementID);
    }

    // 獲取單個公告
    public Announcement getOneAnnouncement(Integer announcementID) {
        Optional<Announcement> optional = repository.findById(announcementID);
        return optional.orElse(null);
    }

 // 獲取所有公告，按創建時間降序排序
    public List<Announcement> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "announcementCreateTime"));
    }

    // 分頁查詢，按創建時間降序排序
    public Page<Announcement> getAllPaginated(Pageable pageable) {
        // 創建一個新的 Pageable 對象，加入排序條件
        Pageable sortedPageable = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Direction.DESC, "announcementCreateTime")
        );
        return repository.findAll(sortedPageable);
    }

    // 按狀態分頁查詢公告
    public Page<Announcement> getAnnouncementsByStatus(Integer status, Pageable pageable) {
        return repository.findByAnnouncementStatus(status, pageable);
    }

    // 按日期範圍分頁查詢公告
    public Page<Announcement> getAnnouncementsByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return repository.findByAnnouncementCreateTimeBetween(startDate, endDate, pageable);
    }

    // 按狀態查詢公告
//    public List<Announcement> getAnnouncementsByStatus(Integer status) {
//        return repository.findByAnnouncementStatus(status);
//    }

    // 按公告對象查詢公告
//    public List<Announcement> getAnnouncementsByTarget(String target) {
//        return repository.findByAnnouncementTarget(target);
//    }

    // 按日期範圍查詢公告
//    public List<Announcement> getAnnouncementsByDateRange(Date startDate, Date endDate) {
//        return repository.findByAnnouncementCreateTimeBetween(startDate, endDate);
//    }

    // 複合查詢
    public List<Announcement> getAll(Map<String, String[]> map) {
        return HibernateUtil_CompositeQuery_Announcement3.getAllC(map, sessionFactory.openSession());
    }
}
