package com.maddog.articket.article.dao;

import com.maddog.articket.article.dto.ArticleQueryCondition;
import com.maddog.articket.article.entity.Article;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDao {

	/**
	 * 新增
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	int insert(Article article);

	/**
	 * 更新
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	int update(Article article);

	/**
	 * 刪除
	 *
	 * @param articleId
	 * 			Integer
	 * @return 成功筆數
	 * 			int
	 */
	int deleteById(Integer articleId);

	/**
	 * 依 ID 查詢
	 *
	 * @param articleId
	 * 			Integer
	 * @return 文章
	 * 			Article
	 */
	Article findById(Integer articleId);

	/**
	 * 查詢所有文章，依創建時間降序排序
	 *
	 * @return 文章清單
	 * 			List<Article>
	 */
	List<Article> findAll();

	List<Article> findByTitle(String title);

	List<Article> findByCondition(ArticleQueryCondition condition);

    @Query("SELECT DISTINCT articleCategory FROM Article WHERE articleCategory IS NOT NULL")
    List<String> findAllCategories();

}