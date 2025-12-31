package com.maddog.articket.announcement.dao;

import com.maddog.articket.announcement.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 公告 DAO
 */
@Mapper
public interface AnnouncementDao {

	/**
	 * 新增
	 *
	 * @param announcement
	 * 			Announcement
	 * @return 成功筆數
	 * 			int
	 */
	int insert(Announcement announcement);

	/**
	 * 更新
	 *
	 * @param announcement
	 * 			Announcement
	 * @return 成功筆數
	 * 			int
	 */
	int update(Announcement announcement);

	/**
	 * 刪除
	 *
	 * @param announcementId
	 * 			Integer
	 * @return 成功筆數
	 * 			int
	 */
	int deleteById(Integer announcementId);

	/**
	 * 依 ID 查詢
	 *
	 * @param announcementId
	 * 			Integer
	 * @return 公告
	 * 			Announcement
	 */
	Announcement findById(Integer announcementId);

	/**
	 * 查詢所有公告，按創建時間降序排序
	 *
	 * @return 公告清單
	 * 			List<Announcement>
	 */
	List<Announcement> findAll();

	/**
	 * 分頁查詢，按創建時間降序排序
	 *
	 * @param offset
	 * 			int
	 * @param limit
	 * 			int
	 * @return 公告清單
	 * 			List<Announcement>
	 */
	List<Announcement> findAllPaginated(@Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 計算總比數
	 *
	 * @return 總比數
	 * 			int
	 */
	int countAll();

}
