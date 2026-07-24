package com.maddog.articket.article.dao;

import com.maddog.articket.article.dto.ArticleForView;
import com.maddog.articket.article.dto.ArticleQueryCondition;
import com.maddog.articket.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章 DAO
 */
@Mapper
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
	List<ArticleForView> findAll();

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
    List<String> findAllCategories();

	/**
	 * 依 ID 查詢 VO
	 *
	 * @param articleId
	 * 			Integer
	 * @return 文章
	 * 			ArticleForView
	 */
	ArticleForView findVoById(Integer articleId);

}