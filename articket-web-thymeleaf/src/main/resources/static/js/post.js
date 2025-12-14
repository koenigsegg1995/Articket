$(document).ready(function() {
	
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
    var isFirstChange = true;

    $('#summernote').summernote({
        height: 300,
        toolbar: [
            ['style', ['bold', 'italic', 'underline']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],			
        ],
        styleTags: ['p', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'],
        placeholder: '在這裡輸入內容...',
        callbacks: {
            onInit: function() {
                // 移除初始的空 p 標籤
                var content = $(this).summernote('code');
                if (content === '<p><br></p>') {
                    $(this).summernote('code', '');
                }
            },
            onChange: function(contents, $editable) {
                // 清空並儲存輸入內容
                var cleanedContent = cleanContent(contents);
                $('#articleContent').val(cleanedContent);
                
                // 只有在用戶開始輸入後才執行驗證
                if (!isFirstChange) {
                    validateContent(cleanedContent);
                } else {
                    isFirstChange = false;
                }
                // 處理 placeholder
                handlePlaceholder($editable);
            }
        }
    });

    function handlePlaceholder($editable) {
        var content = $editable.text().trim();
        if (content.length > 0) {
            $editable.removeClass('placeholder').attr('data-placeholder', '');
        } else {
            $editable.addClass('placeholder').attr('data-placeholder', '在這裡輸入內容...');
        }
    }

    function cleanContent(content) {
        return content
            .replace(/<p><br><\/p>/g, '')
            .replace(/<p>&nbsp;<\/p>/g, '')
            .replace(/(<p>)\s*(<\/p>)/g, '')
            .replace(/^\s*(<p>[\s\S]*?<\/p>)\s*$/, '$1')
            .replace(/<p>/g, '<p style="margin-bottom: 0.5em;">')
            .trim();
    }

    function validateContent(content) {
        var textContent = $('<div>').html(content).text().trim();
        var errorElement = $('#articleContent-error');
        if (errorElement.length === 0) {
            errorElement = $('<div id="articleContent-error" style="color: red; margin-top: 10px;"></div>');
            $('#summernote').after(errorElement);
        }

        if (textContent.length < 1) {
            errorElement.text('文章內容請勿空白').show();
        } else if (textContent.length > 1000) {
            errorElement.text('文章內容長度不能超過1000字').show();
        } else {
            errorElement.hide();
        }
    }

    $('form').on('submit', function(e) {
        var cleanedContent = $('#articleContent').val();
        var textContent = $('<div>').html(cleanedContent).text().trim();
        
        if (textContent.length < 1 || textContent.length > 1000) {
            e.preventDefault();
            validateContent(cleanedContent); // 顯示錯誤訊息
            return false;
        }

        console.log('Submitted content:', cleanedContent);
    });
	
	//取消發文按鈕的事件監聽器
	$('#cancelButton').on('click', function(e) {
	    e.preventDefault();
	    if (confirm('確定要取消發文嗎？未保存的內容將會丟失。')) {
	        window.history.back();
	    }
	});

	// 修改表單提交邏輯，添加取消按鈕的處理
	$('form').on('submit', function(e) {
	    var cleanedContent = $('#articleContent').val();
	    var textContent = $('<div>').html(cleanedContent).text().trim();
	    
	    if (textContent.length < 1 || textContent.length > 1000) {
	        e.preventDefault();
	        validateContent(cleanedContent); // 顯示錯誤訊息
	        return false;
	    }

	    console.log('Submitted content:', cleanedContent);
	});
});