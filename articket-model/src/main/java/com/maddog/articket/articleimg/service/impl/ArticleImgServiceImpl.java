package com.maddog.articket.articleimg.service.impl;

import com.maddog.articket.articleimg.entity.ArticleImg;
import com.maddog.articket.articleimg.dao.ArticleImgDao;
import com.maddog.articket.articleimg.service.pri.ArticleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章圖片 Service Implementation
 */
@Service("articleImgService")
public class ArticleImgServiceImpl implements ArticleImgService {

	/**
	 * 文章圖片 DAO
	 */
	@Autowired
	private ArticleImgDao articleImgDao;

	/**
	 * 新增
	 *
	 * @param articleImg
	 * 			文章圖片 DO
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addArticleImg(ArticleImg articleImg) {
		articleImgDao.insert(articleImg);
	}

	/**
	 * 刪除
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteArticleImg(Integer articleImgId) {
		articleImgDao.delete(articleImgId);
	}

	/**
	 * 依文章圖片 ID 查詢
	 *
	 * @param articleImgId
	 * 			文章圖片 ID
	 * @return 文章圖片 DO
	 */
	@Override
	@Transactional(readOnly = true)
	public ArticleImg getOneArticleImg(Integer articleImgId) {
		return articleImgDao.findById(articleImgId);
	}

	/**
	 * 根據文章 ID 查詢
	 *
	 * @param articleId
	 * 			文章 ID
	 * @return 文章圖片 DO 清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ArticleImg> getArticleImgsByArticleId(Integer articleId) {
        return articleImgDao.findByArticleId(articleId);
    }

	/**
	 * 查詢所有文章圖片 DO
	 *
	 * @return 文章圖片 DO 清單
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ArticleImg> getAll() {
		return articleImgDao.findAll();
	}

}
