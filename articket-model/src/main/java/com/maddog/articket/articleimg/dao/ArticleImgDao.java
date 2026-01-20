package com.maddog.articket.articleimg.dao;

import com.maddog.articket.articleimg.entity.ArticleImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章圖片 DAO
 */
@Mapper
public interface ArticleImgDao {

	/**
	 * 新增
	 *
	 * @param articleImg
	 * 			文章圖片 DO
	 * @return 成功筆數
	 */
	int insert(ArticleImg articleImg);

	/**
	 * 刪除
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 * @return 成功筆數
	 */
	int delete(Integer articleImgId);

	/**
	 * 依文章圖片 ID 查詢
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 * @return 文章圖片 DO
	 */
	ArticleImg findById(Integer articleImgId);

	/**
	 * 根據文章 ID 查詢
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 文章圖片 DO 清單
 	 */
	List<ArticleImg> findByArticleId(Integer articleId);

	/**
	 * 查詢所有文章圖片 DO
	 *
	 * @return 文章圖片 DO 清單
	 */
	List<ArticleImg> findAll();

}