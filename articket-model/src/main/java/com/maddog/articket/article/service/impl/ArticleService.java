package com.maddog.articket.article.service.impl;

import com.maddog.articket.article.dao.ArticleDao;
import com.maddog.articket.article.dto.ArticleQueryCondition;
import com.maddog.articket.article.entity.Article;
import com.maddog.articket.hibernate.util.compositequery.HibernateUtil_CompositeQuery_Article3;
import com.maddog.articket.articleimg.entity.ArticleImg;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleDao articleDao;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addArticle(Article article) {
		articleDao.insert(article);
	}

	public void updateArticle(Article article) {
		articleDao.update(article);
	}

	public int deleteArticle(Integer articleId) {
		return articleDao.deleteById(articleId);
	}

	public Article getOneArticle(Integer articleId) {
		return articleDao.findById(articleId);
	}

	public List<Article> findByCondition() {
		return articleDao.findAll();
	}

	public List<Article> findByTitle(String title){
		return articleDao.findByTitle(title);
	}

	public List<Article> findByCondition(ArticleQueryCondition condition) {
		return articleDao.findByCondition(condition);
	}

    public List<String> getAllCategories() {
        return articleDao.findAllCategories();
    }

}
