package com.maddog.articket.articleimg.service.impl;

import com.maddog.articket.articleimg.entity.ArticleImg;
import com.maddog.articket.articleimg.entity.ArticleImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("articleImgService")
public class ArticleImgService {

	@Autowired
	ArticleImgRepository repository;

	public void addArticleImg(ArticleImg articleImg) {
		repository.save(articleImg);
	}

	public void updateDept(ArticleImg articleImg) {
		repository.save(articleImg);
	}

	public void deleteArticleImg(Integer articleImgID) {
		if (repository.existsById(articleImgID))
			repository.deleteById(articleImgID);
	}


	public ArticleImg getOneArticleImg(Integer articleImgID) {
		Optional<ArticleImg> optional = repository.findById(articleImgID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}
	
	public List<ArticleImg> getArticleImgsByArticleID(Integer articleID) {
        return repository.findByArticle_ArticleID(articleID);
    }

	public List<ArticleImg> getAll() {
		return repository.findAll();
	}



	
}
