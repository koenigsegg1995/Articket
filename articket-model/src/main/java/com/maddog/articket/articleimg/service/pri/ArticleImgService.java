package com.maddog.articket.articleimg.service.pri;

import com.maddog.articket.articleimg.entity.ArticleImg;

import java.util.List;

/**
 * 文章圖片 Service Interface
 */
public interface ArticleImgService {

	/**
	 * 新增
	 *
	 * @param articleImg
	 * 			文章圖片 DO
	 * @return 成功筆數
	 */
	void addArticleImg(ArticleImg articleImg);

	/**
	 * 刪除
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 * @return 成功筆數
	 */
	void deleteArticleImg(Integer articleImgId);

	/**
	 * 依文章圖片 ID 查詢
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 * @return 文章圖片 DO
	 */
	ArticleImg getOneArticleImg(Integer articleImgId);

	/**
	 * 根據文章 ID 查詢
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 文章圖片 DO 清單
	 */
	List<ArticleImg> getArticleImgsByArticleId(Integer articleId);

	/**
	 * 查詢所有文章圖片 DO
	 *
	 * @return 文章圖片 DO 清單
	 */
	List<ArticleImg> getAll();

}
