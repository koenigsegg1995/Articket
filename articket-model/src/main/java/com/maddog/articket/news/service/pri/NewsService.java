package com.maddog.articket.news.service.pri;

import com.maddog.articket.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 最新消息 Service Interface
 */
public interface NewsService {

    /**
     * 新增
     *
     * @param news
     * 			最新消息
     * @return 成功筆數
     */
    int addNews(News news);

    /**
     * 更新
     *
     * @param news
     * 			最新消息
     * @return 成功筆數
     */
    int updateNews(News news);

    /**
     * 刪除
     *
     * @param newsId
     * 			消息ID
     * @return 成功筆數
     */
    int deleteNews(Integer newsId);

    /**
     * 依消息 ID 查詢
     *
     * @param newsId
     * 			消息ID
     * @return 最新消息
     */
    News getOneNews(Integer newsId);

    /**
     * 查詢全部
     *
     * @return 最新消息清單
     */
    List<News> getAll();

    /**
     * 查詢全部並分頁
     *
     * @param pageable
     * 			分頁資訊
     * @return 最新消息清單
     */
    Page<News> getAllPaginated(Pageable pageable);

//    // 複合查詢
//    List<News> getAll(Map<String, String[]> map);

}