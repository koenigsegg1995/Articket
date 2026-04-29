package com.maddog.articket.news.dao;

import com.maddog.articket.news.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 最新消息 DAO
 */
@Mapper
public interface NewsDao {

	/**
	 * 新增
	 *
	 * @param news
	 * 			最新消息
	 * @return 成功筆數
	 */
	int insert(News news);

	/**
	 * 更新
	 *
	 * @param news
	 * 			最新消息
	 * @return 成功筆數
	 */
	int update(News news);

	/**
	 * 刪除
	 *
	 * @param newsId
	 * 			消息ID
	 * @return 成功筆數
	 */
	int deleteById(Integer newsId);

	/**
	 * 依消息 ID 查詢
	 *
	 * @param newsId
	 * 			消息ID
	 * @return 最新消息
	 */
	News findById(Integer newsId);

	/**
	 * 查詢全部並分頁
	 * @param offset
	 * 			分頁起始位置
	 * @param size
	 * 			分頁大小
	 * @return 最新消息清單
	 */
	List<News> findAllPaginated(Integer offset, Integer size );

	/**
	 * 計算全部最新消息筆數
	 *
	 * @return
	 */
	int countAll();

//	List<News> findByCondition();
	
}