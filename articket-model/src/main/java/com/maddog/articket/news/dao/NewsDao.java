package com.maddog.articket.news.dao;

import com.maddog.articket.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsDao {

	int insert(News news);

	int update(News news);

	@Query(value = "delete from News where newsID =?1", nativeQuery = true)
	void deleteById(int newsId);

	// 添加按狀態查詢的方法
	Page<News> findByNewsStatus(Integer status, Pageable pageable);
	
}