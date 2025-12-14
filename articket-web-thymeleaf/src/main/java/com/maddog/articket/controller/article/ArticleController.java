package com.maddog.articket.controller.article;

import com.maddog.articket.article.entity.Article;
import com.maddog.articket.article.service.impl.ArticleService;
import com.maddog.articket.generalmember.entity.GeneralMember;
import com.maddog.articket.generalmember.service.impl.GeneralMemberService;
import com.maddog.articket.articleimg.entity.ArticleImg;
import com.maddog.articket.articleimg.service.impl.ArticleImgService;
import com.maddog.articket.board.entity.Board;
import com.maddog.articket.board.service.impl.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleSvc;
	
	@Autowired
	ArticleImgService articleImgSvc;
	
	@Autowired
	GeneralMemberService generalMemberSvc;

	
	@Autowired
	BoardService boardSvc;


    
	@GetMapping("/forum")
	public String showForumPage(@RequestParam(required = false) Integer boardID, Model model) {
	    List<Article> allArticles = articleSvc.getAll();
	    List<Board> boardList = boardSvc.getAll();

	    // 如果有指定 boardID，則篩選文章
	    if (boardID != null) {
	        allArticles = allArticles.stream()
	            .filter(article -> article.getBoard().getBoardID().equals(boardID))
	            .collect(Collectors.toList());
	    }
	    allArticles.sort(Comparator.comparing(Article::getArticleCreateTime).reversed()); //按文章時間排序
	    model.addAttribute("articleList", allArticles);
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("selectedBoardID", boardID); 

	    return "front-end/forum/forum";
	}
	

    @GetMapping("post")
    public String showPostForm(Model model, HttpSession session) {
        // 會員驗證
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            return "redirect:/generalmember/login";
        }

        // 獲取當前登入的會員信息
        GeneralMember member = generalMemberSvc.getByMemberAccount(memberAccount);
        if (member == null) {
            return "redirect:/generalmember/login";
        }
        
        model.addAttribute("article", new Article());
        
        List<String> articleCategories = articleSvc.getAllCategories();
        model.addAttribute("articleCategories", articleCategories);
        
        List<Board> boardList = boardSvc.getAll();
        model.addAttribute("boardList", boardList);
        
//        List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
//	    model.addAttribute("generalMemberListData", generalMemberList);
        
        return "front-end/forum/post";
    }
    
    
    @GetMapping("OneArticle/{articleID}")
    public String showOneArticle(@PathVariable("articleID") String articleID, ModelMap model) {
        try {
            int id = Integer.parseInt(articleID);
            Article article = articleSvc.getOneArticle(id);
            
            if (article == null) {
                model.addAttribute("errorMessage", "查無資料");
                return "front-end/forum/forum";
            }            
            
    		List<Article> list = articleSvc.getAll();
    		model.addAttribute("articleListData", list); 
    		model.addAttribute("board", new Board()); 
    		
    		List<Board> list2 = boardSvc.getAll();
        	model.addAttribute("boardListData",list2);   
        	
    	    
    	    List<String> categories = articleSvc.getAllCategories();
    	    model.addAttribute("articleCategories", categories);
    		
    	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
    	    model.addAttribute("generalMemberListData", generalMemberList);
    		          
            
            model.addAttribute("article", article);
            return "front-end/forum/OneArticle";
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "無效的文章ID");
            return "front-end/forum/forum";
        }
    }
    

    

//    @GetMapping("/select_page")
//	public String select_page(Model model) {
//		return "front-end/forum/select_page";
//	}
//    
//    @GetMapping("/listAllArticle")
//	public String listAllArticle(Model model) {
//		return "front-end/forum/listAllArticle";
//	}
    
    
    
    @ModelAttribute("articleListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<Article> referenceListData(Model model) {
		
    	List<Article> list = articleSvc.getAll();
		return list;
	}
    
	@ModelAttribute("boardListData") // for select_page.html 第135行用
	protected List<Board> referenceListData_Board(Model model) {
		model.addAttribute("board", new Board()); // for select_page.html 第133行用
		List<Board> list = boardSvc.getAll();
		return list;
	}
	

	
	
	@GetMapping("addArticle")
	public String addArticle(ModelMap model) {
		Article article = new Article();
		model.addAttribute("article", article);
		
		List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardListData", boardList);
	    
	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);
		
	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberListData", generalMemberList);
	    
	    
		return "front-end/forum/addArticle";
	}
	

	
	

	@PostMapping("insert")
	public String insert(@Valid Article article, BindingResult result, ModelMap model,
			@RequestParam(value = "articlePic", required = false) MultipartFile[] parts,
			HttpSession session) throws IOException {
		
	       // 獲取當前登入的會員信息
        String memberAccount = (String) session.getAttribute("memberAccount");
        if (memberAccount == null || memberAccount.isEmpty()) {
            model.addAttribute("error", "會話已過期，請重新登入");
            return "redirect:/generalmember/login";
        }

        GeneralMember generalMember = generalMemberSvc.getByMemberAccount(memberAccount);
        if (generalMember == null) {
            model.addAttribute("error", "無法找到會員信息，請重新登入");
            return "redirect:/generalmember/login";
        }

        // 設置文章的作者
        article.setGeneralMember(generalMember);


		    if (result.hasErrors()) {
		    	setCommonModelAttributes(model);
		        return "front-end/forum/post";
		    }
		    		    
		 // 檢查圖片數量
		    final int MAX_IMAGES = 5; // 最多允許5張圖片
		    if (parts != null && parts.length > MAX_IMAGES) {
		    	result.rejectValue("articlePic", "error.articlePic", "最多只能上傳 " + MAX_IMAGES + " 張圖片");
		    	setCommonModelAttributes(model);
		        return "front-end/forum/post";
		    }

		    // 檢查圖片大小和格式
		    final long MAX_FILE_SIZE = 8 * 1024 * 1024; // 最大8MB
		    if (parts != null) {
		        for (MultipartFile pic : parts) {
		            if (!pic.isEmpty()) {
		            	if (!pic.isEmpty() && pic.getSize() > MAX_FILE_SIZE) {
		                    result.rejectValue("articlePic", "error.articlePic", "圖片大小不能超過 8MB");
		                    setCommonModelAttributes(model);
		                    return "front-end/forum/post";
		                }

		            }
		        }
		    }
		    
		/*************************** 2.開始新增資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.addArticle(article);
		
		if (parts != null && parts.length > 0) {
	        for (MultipartFile pic : parts) {
	            if (pic != null && !pic.isEmpty()) {
	                try {
	                    ArticleImg articleImg = new ArticleImg();
	                    articleImg.setArticle(article);  // 使用原始的 article 對象
	                    articleImg.setArticlePic(pic.getBytes());
	                    articleImgSvc.addArticleImg(articleImg);
	                } catch (IOException e) {
	                    // 記錄錯誤並繼續處理其他圖片
	                    model.addAttribute("warningMessage", "部分圖片上傳失敗：" + pic.getOriginalFilename());
	                }
	            }
	        }

	    }
			
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/article/forum"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/forum/listAllArticle")
	}
	

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(
			@RequestParam("articleID") String articleIDStr,
//			@RequestParam(value = "deleteImageID", required = false) Integer deleteImageID,
			ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		
		Integer articleID = Integer.valueOf(articleIDStr);
		
		// 如果有刪除圖片請求
//	    if (deleteImageID != null) {
//	        articleImgSvc.deleteArticleImg(deleteImageID); // 删除指定 ID 的图片
//	    }
		
		/*************************** 2.開始查詢資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		Article article = articleSvc.getOneArticle(articleID);
		
		List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardListData", boardList);
	    
	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);
	    
	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberListData", generalMemberList);
	    
	   // 查詢對應文章的所有圖片
	    List<ArticleImg> articleImgs = articleImgSvc.getArticleImgsByArticleID(articleID);
	    model.addAttribute("articleImgs", articleImgs);

		

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("article", article);
		return "front-end/forum/update_article_input"; // 查詢完成後轉交update_emp_input.html
	}

	
	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	
	
	@PostMapping("update")
	public String update(@Valid Article article, BindingResult result, ModelMap model,
			@RequestParam("articlePic") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
//		result = removeFieldError(article, result, "articlePic");
		

		if (result.hasErrors()) {
			setCommonModelAttributes(model);
			return "front-end/forum/update_article_input";
		}
		
		 // 檢查圖片數量
	    final int MAX_IMAGES = 5; // 最多允許5張圖片
	    if (parts != null && parts.length > MAX_IMAGES) {
	        result.rejectValue("articlePic", "error.articlePic", "最多只能上傳 " + MAX_IMAGES + " 張圖片");
	        return "front-end/forum/addArticle";
	    }

	    // 檢查圖片大小
	    final long MAX_FILE_SIZE = 8 * 1024 * 1024; // 最大8MB
	    if (parts != null) {
	        for (MultipartFile pic : parts) {
	            if (!pic.isEmpty()) {
	                if (pic.getSize() > MAX_FILE_SIZE) {
	                    result.rejectValue("articlePic", "error.articlePic", "圖片大小不能超過 8MB");
	                    return "front-end/forum/addArticle";
	                }
	            }
	        }
	    }
		
		/*************************** 2.開始修改資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.updateArticle(article);
		
		// 處理圖片
	    if (parts != null && parts.length > 0 && !parts[0].isEmpty()) {
	        // 如果有新圖片上傳,先刪除舊圖片
	        articleImgSvc.deleteArticleImg(article.getArticleID());

	        for (MultipartFile pic : parts) {
	            if (pic != null && !pic.isEmpty()) {
	                try {
	                    ArticleImg articleImg = new ArticleImg();
	                    articleImg.setArticle(article);
	                    articleImg.setArticlePic(pic.getBytes());
	                    articleImgSvc.addArticleImg(articleImg);
	                } catch (IOException e) {
	                    // 記錄錯誤並繼續處理其他圖片
	                    model.addAttribute("warningMessage", "部分圖片上傳失敗：" + pic.getOriginalFilename());
	                }
	            }
	        }
	    }
	    // 如果沒有新圖片上傳,保留原有圖片,不做任何處理
		
		

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		article = articleSvc.getOneArticle(Integer.valueOf(article.getArticleID()));
		model.addAttribute("article", article);
		return "front-end/forum/OneArticle"; // 修改成功後轉交listOneArticle.html
	}

	/*
	 * This method will be called on listAllArticle.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("articleID") String articleID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.deleteArticle(Integer.valueOf(articleID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "front-end/forum/forum"; // 刪除完成後轉交listAllEmp.html
	}
	
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	
	
	//簡單文章標題查詢
	@GetMapping("search")
	public String searchArticlesByTitle(@RequestParam(required = false) String title, Model model) {
		System.out.println("Received search request for title: " + title);
		
		Map<String, String[]> map = new HashMap<>();
	    if (title != null && !title.isEmpty()) {
	    	map.put("articleTitle", new String[]{title});
	    }
	    
	    List<Article> articleList = articleSvc.getAll(map);
	    
//	    System.out.println("Search results count: " + articleList.size());
//	    for (Article article : articleList) {
//	        System.out.println("Article found: " + article.getArticleTitle());
//	    }

	    model.addAttribute("articleList", articleList);
	    
	  //顯示用戶搜索的內容
	    model.addAttribute("searchTitle", title);

	    List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardList", boardList);	    
	    
	    System.out.println("Model attributes: " + model.asMap().keySet());

	    return "front-end/forum/forum";
	}
	
	//複合查詢
	@PostMapping("listArticles_ByCompositeQuery")
	public String listAllArticle(HttpServletRequest req, Model model) {
	    Map<String, String[]> map = req.getParameterMap();

	    List<Article> articleList = articleSvc.getAll(map);
	    model.addAttribute("articleList", articleList);  // 改為 articleList

//	    System.out.println("Search results count: " + articleList.size());
//	    for (Article article : articleList) {
//	        System.out.println("Article found: " + article.getArticleTitle());
//	    }

	    List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardList", boardList);  // 改為 boardList

	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);

	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberList", generalMemberList);  // 改為 generalMemberList

	    // 添加搜索條件到模型
	    model.addAttribute("searchTitle", req.getParameter("articleTitle"));
	    model.addAttribute("searchCategory", req.getParameter("articleCategory"));
	    model.addAttribute("searchBoardID", req.getParameter("boardID"));

	    return "front-end/forum/forum";
	}
	
	
	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="文章編號: 請勿空白")
		@Digits(integer = 4, fraction = 0, message = "文章編號: 請填數字-請勿超過{integer}位數")
		@Min(value = 1, message = "文章編號: 不能小於{value}")
		@Max(value = 100, message = "文章編號: 不能超過{value}")
		@RequestParam("articleID") String articleID,
		ModelMap model) {
		
		
		/***************************2.開始查詢資料*********************************************/
//		ArticleService articleSvc = new ArticleService();
		Article article = articleSvc.getOneArticle(Integer.valueOf(articleID));
		
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("board", new Board());  // for select_page.html 第133行用
		
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);    // for select_page.html 第135行用
    	
	    
	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);
		
	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberListData", generalMemberList);
		
		if (article == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "front-end/forum/forum";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("article", article);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "front-end/forum/listOneArticle";  // 查詢完成後轉交listOneArticle.html
		return "front-end/forum/OneArticle"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
	}
	

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="articleID" id="articleID" items="${generalMemberListData}" itemValue="memberID" itemLabel="memberName" />
	 */
//	@ModelAttribute("generalMemberListData")
//	protected List<GeneralMember> referenceListData() {
//		// GeneralMemberService generalMemberSvc = new GeneralMemberService();
//		List<GeneralMember> list = generalMemberSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : 
	 * <form:select path="memberID" id="memberID" items="${generalemberMapData}" />
	 */
	@ModelAttribute("articleCategoryMapData") //
	protected Map<Integer, String> referenceMapData() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(1, "公告");
		map.put(2, "情報");
		map.put(3, "影音");
		map.put(4, "閒聊");
		return map;
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(Article article, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(article, "article");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	


	//錯誤訊息處理顯示
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第267~271行是當前面第252行返回 /src/main/resources/templates/front-end/forum/select_page.html用的 ====   
    	ArticleService articleSvc = new ArticleService();
    	model.addAttribute("article", new ArticleService());
    	
	    // 加載所有文章
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("board", new Board());  // for select_page.html 第133行用
		
		// 加載所有版塊
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);    // for select_page.html 第135行用
		
    	
    	String message = strBuilder.toString();
	    return new ModelAndView("front-end/forum/post", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
	
	   //設置通用的model
	   private void setCommonModelAttributes(ModelMap model) {
	        model.addAttribute("articleListData", articleSvc.getAll());
	        model.addAttribute("boardListData", boardSvc.getAll());
	        model.addAttribute("articleCategories", articleSvc.getAllCategories());
	        model.addAttribute("generalMemberListData", generalMemberSvc.getAll());
	    }
	

}