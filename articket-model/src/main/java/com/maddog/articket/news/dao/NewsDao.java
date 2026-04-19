package com.maddog.articket.news.dao;

import com.maddog.articket.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsDao {

	int insert(News news);

	int update(News news);

	@Query(value = "delete from News where newsID =?1", nativeQuery = true)
	void deleteById(Integer newsId);

	News findById(Integer newsId);

	// 	ORDER BY news_create_time desc
	List<News> findAllPaginated(Integer offset, Integer size );

	int countAll();

//	List<News> findByCondition();
	
}