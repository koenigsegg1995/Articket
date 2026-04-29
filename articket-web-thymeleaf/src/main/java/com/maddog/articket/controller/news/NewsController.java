package com.maddog.articket.controller.news;

import com.maddog.articket.news.entity.News;
import com.maddog.articket.news.service.pri.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 最新消息 Controller
 */
@Controller
@RequestMapping("/news")
@Validated
public class NewsController {

    /**
     * 最新消息 Service
     */
    @Autowired
    private NewsService newsSvc;

    /**
     * 管理員消息頁面 沒有側邊攔
     *
     * @param session
     *          HttpSession
     * @param model
     *          Model
     * @param page
     *          頁碼
     * @return adminLogin.html
     */
    @GetMapping("/listAllNews")
    public String listAllNews(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
    	if(session.getAttribute("administratorID") == null) {
    		return "redirect:/adminLogin";
    	}

        int pageSize = 10; // 每頁顯示的公告數量
        Page<News> newsPage = newsSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("news", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "back-end-admin/announcement-news/news";
    }

    /**
     * 首頁消息頁面
     *
     * @param model
     *          Model
     * @param page
     *          頁碼
     * @return news.html
     */
    @GetMapping("/allNews")
    public String allNews(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5; // 每頁顯示的公告數量
        Page<News> newsPage = newsSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("newsList", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "front-end/announcement-news/news";
    }

    /**
     * 新增消息頁面
     *
     * @param session
     *          HttpSession
     * @param model
     *          Model
     * @return addNews.html
     */
    @GetMapping("addNews")
    public String addNews(HttpSession session, ModelMap model) {
        // 檢查是否登入
        Integer administratorId = (Integer) session.getAttribute("administratorID");
        if (administratorId == null) {
            return "redirect:/adminLogin";
        }

        News news = new News();
        news.setAdministratorId(administratorId);
        model.addAttribute("news", news);

        return "back-end-admin/announcement-news/addNews";
    }

    /**
     * 處理新增消息
     *
     * @param news
     *          最新消息
     * @param result
     *          BindingResult
     * @param session
     *          HttpSession
     * @param model
     *          Model
     * @return addNews.html
     */
    @PostMapping("insert")
    public String insert(@Valid News news, BindingResult result, HttpSession session, ModelMap model) {
        // 再次檢查是否登入
        Integer administratorId = (Integer) session.getAttribute("administratorID");
        if (administratorId == null) {
            return "redirect:/adminLogin";
        }

        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/addNews";
        }

        // 設置管理員ID
        news.setAdministratorId(administratorId);

        newsSvc.addNews(news);

        model.addAttribute("success", "- (新增成功)");

        return "redirect:/news/listAllNews";
    }

    /**
     * 獲取要更新的消息
     *
     * @param newsId
     *          消息ID
     * @param session
     *          HttpSession
     * @param model
     *          Model
     * @return updateNews.html
     */
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("newsID") Integer newsId, HttpSession session, ModelMap model) {
        // 檢查是否登入
        Integer administratorId = (Integer) session.getAttribute("administratorID");
        if (administratorId == null) {
            return "redirect:/adminLogin";
        }

        News news = newsSvc.getOneNews(newsId);

        if (!news.getAdministratorId().equals(administratorId)) {
            model.addAttribute("error", "您沒有權限更新這個公告");
            return "back-end-admin/announcement-news/news"; // 返回列表頁面
        }

        model.addAttribute("news", news);

        return "back-end-admin/announcement-news/updateNews";
    }

    /**
     * 處理消息更新
     *
     * @param news
     *          最新消息
     * @param result
     *          BindingResult
     * @param session
     *          HttpSession
     * @param model
     *          Model
     * @return updateNews.html
     */
    @PostMapping("update")
    public String update(@Valid News news, BindingResult result, HttpSession session, ModelMap model) {
        // 檢查是否登入
        Integer administratorId = (Integer) session.getAttribute("administratorID");
        if (administratorId == null) {
            return "redirect:/adminLogin";
        }

        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/updateNews";
        }

        // 設置當前登入的管理員ID
        news.setAdministratorId(administratorId);

        newsSvc.updateNews(news);

        model.addAttribute("success", "- (修改成功)");

        return "redirect:/news/listAllNews";
    }

    /**
     * 刪除最新消息
     *
     * @param newsId
     *          消息ID
     * @param model
     *          Model
     * @return listAllNews.html
     */
    @PostMapping("delete")
    public String delete(@RequestParam("newsID") Integer newsId, ModelMap model) {
        newsSvc.deleteNews(newsId);

        List<News> list = newsSvc.getAll();

        model.addAttribute("newsListData", list);
        model.addAttribute("success", "- (刪除成功)");

        return "front-end/announcement-news/listAllNews";
    }

//    @PostMapping("listNewss_ByCompositeQuery")
//    public String listAllNews(HttpServletRequest req, Model model) {
//        Map<String, String[]> map = req.getParameterMap();
//        List<News> list = newsSvc.getAll(map);
//        model.addAttribute("newsListData", list);
//        return "back-end/news/listAllNews";
//        return "front-end/announcement-news/listAllNews";
//
//    }

    /**
     * 顯示一則最新消息
     *
     * @param newsId
     *          消息ID
     * @param model
     *          Model
     * @return select_page.html
     */
    @PostMapping("getOne_For_Display")
    public String getOne_For_Display(
            @NotEmpty(message="公告編號: 請勿空白")
            @Digits(integer = 4, fraction = 0, message = "公告編號: 請填數字-請勿超過{integer}位數")
            @Min(value = 1, message = "公告編號: 不能小於{value}")
            @Max(value = 1000, message = "公告編號: 不能超過{value}")
            @RequestParam("newsID") Integer newsId,
            ModelMap model) {

        News news = newsSvc.getOneNews(newsId);

        if (news == null) {
            model.addAttribute("errorMessage", "查無資料");

            return "front-end/announcement-news/select_page";

        }

        model.addAttribute("news", news);
        model.addAttribute("getOne_For_Display", "true");

        return "front-end/announcement-news/select_page";
    }

    /**
     * 顯示所有最新消息
     *
     * @return 最新消息清單
     */
    @ModelAttribute("newsListData")
    public List<News> referenceListData() {
        return newsSvc.getAll();
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "<br>");
        }

        List<News> list = newsSvc.getAll();
        model.addAttribute("newsListData", list);

        String message = strBuilder.toString();
        return new ModelAndView("front-end/news/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
    }

}