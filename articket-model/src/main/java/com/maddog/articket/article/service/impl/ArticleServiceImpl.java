package com.maddog.articket.article.service.impl;

import com.maddog.articket.article.dao.ArticleDao;
import com.maddog.articket.article.dto.ArticleQueryCondition;
import com.maddog.articket.article.entity.Article;
import com.maddog.articket.article.service.pri.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	/**
	 * 文章 DAO
	 */
	@Autowired
	private ArticleDao articleDao;

	/**
	 * 新增
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addArticle(Article article) {
		return articleDao.insert(article);
	}

	/**
	 * 更新
	 *
	 * @param article
	 * 			Article
	 * @return 成功筆數
	 * 			int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateArticle(Article article) {
		return articleDao.update(article);
	}

	/**
	 * 刪除
	 *
	 * @param articleId
	 * 			Integer
	 * @return 成功筆數
	 * 			int
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteArticle(Integer articleId) {
		return articleDao.deleteById(articleId);
	}

	/**
	 * 依 ID 查詢
	 *
	 * @param articleId
	 * 			Integer
	 * @return 文章
	 * 			Article
	 */
	@Override
	@Transactional(readOnly = true)
	public Article getOneArticle(Integer articleId) {
		return articleDao.findById(articleId);
	}

	/**
	 * 查詢所有文章，依創建時間降序排序
	 *
	 * @return 文章清單
	 * 			List<Article>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Article> findByCondition() {
		return articleDao.findAll();
	}

	/**
	 * 依標題查詢，依創建時間降序排序
	 *
	 * @param title
	 * 			String
	 * @return 文章清單
	 * 			List<Article>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Article> findByTitle(String title){
		return articleDao.findByTitle(title);
	}

	/**
	 * 依條件查詢，依創建時間降序排序
	 *
	 * @param condition
	 * 			ArticleQueryCondition
	 * @return 文章清單
	 * 			List<Article>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Article> findByCondition(ArticleQueryCondition condition) {
		return articleDao.findByCondition(condition);
	}
	/**
	 * 查詢現有文章類別
	 *
	 * @return 現有文章類別清單
	 * 			List<String>
	 */
	@Override
	@Transactional(readOnly = true)
    public List<String> getAllCategories() {
        return articleDao.findAllCategories();
    }

}
