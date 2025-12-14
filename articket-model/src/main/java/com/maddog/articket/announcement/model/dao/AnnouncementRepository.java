package com.maddog.articket.announcement.model.dao;

import com.maddog.articket.announcement.model.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete from Announcement where announcementID =?1", nativeQuery = true)
	void deleteByAnnouncementID(int announcementID);

	// 添加按狀態查詢的方法
	Page<Announcement> findByAnnouncementStatus(Integer status, Pageable pageable);

	// 添加按創建時間範圍查詢的方法
	Page<Announcement> findByAnnouncementCreateTimeBetween(Date startDate, Date endDate, Pageable pageable);
}
