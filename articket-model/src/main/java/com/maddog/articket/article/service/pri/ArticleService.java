package com.maddog.articket.article.service.pri;

import com.maddog.articket.article.dto.ArticleQueryCondition;
import com.maddog.articket.article.entity.Article;

import java.util.List;

public interface ArticleService {

	/**
	 * 新增
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	int addArticle(Article article);

	/**
	 * 更新
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	int updateArticle(Article article);

	/**
	 * 刪除
	 *
	 * @param articleId
	 * 			Integer
	 * @return 成功筆數
	 * 			int
	 */
	int deleteArticle(Integer articleId);

	/**
	 * 依 ID 查詢
	 *
	 * @param articleId
	 * 			Integer
	 * @return 文章
	 * 			Article
	 */
	Article getOneArticle(Integer articleId);

	/**
	 * 查詢所有文章，依創建時間降序排序
	 *
	 * @return 文章清單
	 * 			List<Article>
	 */
	List<Article> findByCondition();

	/**
	 * 依標題查詢，依創建時間降序排序
	 *
	 * @param title
	 * 			String
	 * @return 文章清單
	 * 			List<Article>
	 */
	List<Article> findByTitle(String title);

	/**
	 * 依條件查詢，依創建時間降序排序
	 *
	 * @param condition
	 * 			ArticleQueryCondition
	 * @return 文章清單
	 * 			List<Article>
	 */
	List<Article> findByCondition(ArticleQueryCondition condition);
	/**
	 * 查詢現有文章類別
	 *
	 * @return 現有文章類別清單
	 * 			List<String>
	 */
    List<String> getAllCategories();

}
