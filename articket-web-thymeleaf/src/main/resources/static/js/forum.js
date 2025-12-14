document.addEventListener('DOMContentLoaded', function() {
    // 初始化所有的 tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });


    // 載入 header
    fetch('/header')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('header').innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading header:', error);
            document.getElementById('header').innerHTML = '<p>Error loading header</p>';
        });

    // 載入 footer
    fetch('/footer')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('footer').innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading footer:', error);
            document.getElementById('footer').innerHTML = '<p>Error loading footer</p>';
        });

		// 討論版分類點擊切換
		const listItems = document.querySelectorAll('.list-group-item');
		
		const urlParams = new URLSearchParams(window.location.search);
		const selectedBoardID = urlParams.get('boardID');
		
		if (selectedBoardID) {
		    const selectedBoard = document.querySelector(`.list-group-item[data-board-id="${selectedBoardID}"]`);
		    if (selectedBoard) {
		        updateBreadcrumb(selectedBoard.getAttribute('data-board-name'));
		    }
		} else {
		    updateBreadcrumb('Home');
		}

		listItems.forEach(item => {
			if (item.getAttribute('data-board-id') === selectedBoardID) {
			           item.classList.add('active');
			           updateBreadcrumb(item.getAttribute('data-board-name'));
			       }
				   
		    item.addEventListener('click', function (event) {
		        event.preventDefault();
				
		        // 移除所有項目的 active 類				
		        listItems.forEach(i => i.classList.remove('active'));
				
		        // 為當前點擊的項目添加 active 類
		        this.classList.add('active');
		        
		        // 更新麵包屑
		        updateBreadcrumb(this.getAttribute('data-board-name'));
				
				// 獲取 boardID
				const boardID = this.getAttribute('data-board-id');
					
				// 構建新的 URL
				const currentUrl = new URL(window.location.href);
				if (boardID) {
				  currentUrl.searchParams.set('boardID', boardID);
				} else {
				  currentUrl.searchParams.delete('boardID');
				}
				
				 // 重新載入頁面
				 window.location.href = currentUrl.toString();
		        
		    });
		});
		
			
		
		// 獲取所有按讚數和收藏數的元素
		const likeCounts = document.querySelectorAll('.like-count');
		const favoriteCounts = document.querySelectorAll('.favorite-count');

		// 獲取按讚數
		likeCounts.forEach(element => {
		    const articleID = element.getAttribute('data-article-id');
		    fetch(`/heart/count/${articleID}`)
		        .then(response => response.json())
		        .then(count => {
		            element.textContent = count;
		        })
		        .catch(error => {
		            console.error('Error fetching like count:', error);
		            element.textContent = 'N/A';
		        });
		});

		// 獲取收藏數
		favoriteCounts.forEach(element => {
		    const articleID = element.getAttribute('data-article-id');
		    fetch(`/articleCollection/count/${articleID}`)
		        .then(response => response.json())
		        .then(count => {
		            element.textContent = count;
		        })
		        .catch(error => {
		            console.error('Error fetching favorite count:', error);
		            element.textContent = 'N/A';
		        });
		});
		
		
		
		// 更新麵包屑導航
				function updateBreadcrumb(boardName) {
				    const currentBoard = document.getElementById('currentBoard');
					if (currentBoard) {
					       currentBoard.textContent = boardName || 'Home';
					   }
				}
				


				
				// 分頁
				var $articlesList = $('.list');
				var $paginationContainer = $('#paginationContainer');
				var itemsPerPage = 10;
				var currentPage = 1;
				var allArticles = [];
				var filteredArticles = [];

				// 初始化分頁
				function initializePagination() {
				    allArticles = $articlesList.find('tr').toArray();
				    filteredArticles = allArticles;
				    renderPagination();
				    goToPage(1);
				}

				// 渲染分頁控件
				function renderPagination() {
				      var pageCount = Math.ceil(filteredArticles.length / itemsPerPage);
				      var html = '';
				      for (var i = 1; i <= pageCount; i++) {
				          html += '<li class="page-item' + (currentPage === i ? ' active' : '') + '">' +
				                  '<a class="page-link" href="#" data-page="' + i + '">' + i + '</a></li>';
				      }
				      $paginationContainer.html(html);
				  }

				// 切換到指定頁面
				function goToPage(page) {
				        currentPage = page;
				        var startIndex = (page - 1) * itemsPerPage;
				        var endIndex = startIndex + itemsPerPage;
				        
				        $articlesList.find('tr').addClass('d-none');
				        $(filteredArticles.slice(startIndex, endIndex)).removeClass('d-none');
				        
				        $paginationContainer.find('.page-item').removeClass('active');
				        $paginationContainer.find('.page-item:eq(' + (page - 1) + ')').addClass('active');
				        
				        updateArticleNumbers(startIndex);
				    }

				// 更新文章編號
				function updateArticleNumbers(startIndex) {
				    $articlesList.find('tr:not(.d-none)').each(function(index) {
				        $(this).find('th.index').text(startIndex + index + 1);
				    });
				}

				// 綁定分頁點擊事件
				$paginationContainer.on('click', '.page-link', function(e) {
				    e.preventDefault();
				    var page = parseInt($(this).data('page'));
				    goToPage(page);
				});
				
				// 文章類別頁籤切換
				const categoryButtons = document.querySelectorAll('.btn-group .btn');


				categoryButtons.forEach(button => {
				    button.addEventListener('click', function(event) {
				        event.preventDefault();


				        categoryButtons.forEach(btn => btn.classList.remove('active'));
				        this.classList.add('active');
				        const category = this.getAttribute('data-category');
				        filterArticles(category);
				    });
				});

				function filterArticles(category) {
				    filteredArticles = allArticles.filter(article => {
				        const articleCategory = $(article).attr('data-category');

				        return category === '全部' || articleCategory === category;
				    });

	

				    $articlesList.find('tr').addClass('d-none');
				    filteredArticles.forEach(article => {
				        $(article).removeClass('d-none');
				    });

				    renderPagination();
				    goToPage(1);


				}

					
					// 初始化分頁
					 initializePagination();
					 
				    // 初始化時顯示所有文章
				    filterArticles('全部');
				});
		
				// 點擊發文按鈕跳轉發文頁面
				function goToAddArticle() {
				  window.location.href = 'post';
				}

		
		
