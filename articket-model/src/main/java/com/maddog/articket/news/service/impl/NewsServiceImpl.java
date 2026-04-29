package com.maddog.articket.news.service.impl;

import com.maddog.articket.news.dao.NewsDao;
import com.maddog.articket.news.entity.News;
import com.maddog.articket.news.service.pri.NewsService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 最新消息 Service Implementation
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

    /**
     * 最新消息 DAO
     */
    @Autowired
    private NewsDao newsDao;

    /**
     * 新增
     *
     * @param news
     * 			最新消息
     * @return 成功筆數
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addNews(News news) {
        return newsDao.insert(news);
    }

    /**
     * 更新
     *
     * @param news
     * 			最新消息
     * @return 成功筆數
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateNews(News news) {
        return newsDao.update(news);
    }

    /**
     * 刪除
     *
     * @param newsId
     * 			消息ID
     * @return 成功筆數
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteNews(Integer newsId) {
        return newsDao.deleteById(newsId);
    }

    /**
     * 依消息 ID 查詢
     *
     * @param newsId
     * 			消息ID
     * @return 最新消息
     */
    @Override
    @Transactional(readOnly = true)
    public News getOneNews(Integer newsId) {
        return newsDao.findById(newsId);
    }

    /**
     * 查詢全部
     *
     * @return 最新消息清單
     */
    @Override
    @Transactional(readOnly = true)
    public List<News> getAll() {
        return newsDao.findAllPaginated(null, null);
    }

    /**
     * 查詢全部並分頁
     *
     * @param pageable
     * 			分頁資訊
     * @return 最新消息清單
     */
    @Override
    @Transactional(readOnly = true)
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