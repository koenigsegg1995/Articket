package com.maddog.articket.news.service.impl;

import com.maddog.articket.news.dao.NewsDao;
import com.maddog.articket.news.entity.News;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("newsService")
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private SessionFactory sessionFactory;

    // 創建新消息
    public void addNews(News news) {
        newsDao.save(news);
    }

    // 更新現有消息
    public void updateNews(News news) {
        newsDao.save(news);
    }

    // 刪除消息
    public void deleteNews(Integer newsId) {
        newsDao.deleteById(newsId);
    }

    // 獲取單個消息
    public News getOneNews(Integer newsID) {
        Optional<News> optional = newsDao.findById(newsID);
        return optional.orElse(null);
    }

    // 獲取所有消息
    public List<News> getAll() {
        return newsDao.findAll(Sort.by(Sort.Direction.DESC, "newsCreateTime"));
    }
    // 分頁查詢
    public Page<News> getAllPaginated(Pageable pageable) {
    	// 創建一個新的 Pageable 對象，加入排序條件
        Pageable sortedPageable = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Direction.DESC, "newsCreateTime")
        );
        return newsDao.findAll(sortedPageable);
    }

    // 複合查詢
    public List<News> getAll(Map<String, String[]> map) {
        // 假設您有一個類似的 News 工具類
        // 您可能需要創建這個工具類或調整查詢方法
        return newsDao.getAllC(map, sessionFactory.openSession());
    }

}