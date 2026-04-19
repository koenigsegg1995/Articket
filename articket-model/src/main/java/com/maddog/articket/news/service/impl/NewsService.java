package com.maddog.articket.news.service.impl;

import com.maddog.articket.news.dao.NewsDao;
import com.maddog.articket.news.entity.News;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("newsService")
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private SessionFactory sessionFactory;

    // 創建新消息
    public void addNews(News news) {
        newsDao.insert(news);
    }

    // 更新現有消息
    public void updateNews(News news) {
        newsDao.update(news);
    }

    // 刪除消息
    public void deleteNews(Integer newsId) {
        newsDao.deleteById(newsId);
    }

    // 獲取單個消息
    public News getOneNews(Integer newsId) {
        return newsDao.findById(newsId);
    }

    // 獲取所有消息
    public List<News> getAll() {
        return newsDao.findAllPaginated(null, null);
    }

    // 分頁查詢
    public Page<News> getAllPaginated(Pageable pageable) {
        // 查詢分頁結果
        List<News> result = newsDao.findAllPaginated((int)pageable.getOffset(), pageable.getPageSize());

        // 總筆數
        int total = newsDao.countAll();

        return new PageImpl<>(result, pageable, total);
    }

//    // 複合查詢
//    public List<News> getAll(Map<String, String[]> map) {
//        return newsDao.findByCondition();
//    }

}