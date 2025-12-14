document.addEventListener('DOMContentLoaded', function() {
    const sidebarPlaceholder = document.getElementById('sidebar-placeholder');
    if (sidebarPlaceholder) {
        fetch('/adminSidebar')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.text();
            })
            .then(data => {
                sidebarPlaceholder.innerHTML = data;
            })
            .catch(error => {
                console.error('Error loading sidebar:', error);
                sidebarPlaceholder.innerHTML = 'Error loading sidebar content';
            });
    } else {
        console.error('Sidebar placeholder element not found');
    }
});


// admin_prosecute.js

//$(document).ready(function() {
//    loadProsecutes();
//
//    $(document).on('click', '.btn-process', function() {
//        var id = $(this).data('id');
//        processProsecute(id);
//    });
//
//    $(document).on('click', '.btn-delete', function() {
//        var id = $(this).data('id');
//        deleteProsecute(id);
//    });
//});

//function loadProsecutes() {
//    $.ajax({
//        url: '/prosecutes',
//        type: 'GET',
//        dataType: 'json',
//        success: function(data) {
//            console.log('Received data:', data);
//            var tbody = $('.report-table tbody');
//            tbody.empty();
//            if (Array.isArray(data) && data.length > 0) {
//                data.forEach(function(prosecute) {
//                    var row = `
//                        <tr>
//                            <td>${prosecute.prosecuteID || 'N/A'}</td>
//                            <td>${prosecute.articleId || 'N/A'}</td>
//                            <td>${prosecute.messageId || 'N/A'}</td>
//                            <td>${prosecute.prosecuteReason || 'N/A'}</td>
//                            <td>${formatDate(prosecute.prosecuteCreateTime) || 'N/A'}</td>
//                            <td>
//                                <button class="btn btn-primary btn-sm btn-process" data-id="${prosecute.prosecuteID}">處理</button>
//                                <button class="btn btn-danger btn-sm btn-delete" data-id="${prosecute.prosecuteID}">刪除</button>
//                            </td>
//                        </tr>
//                    `;
//                    tbody.append(row);
//                });
//            } else {
//                tbody.append('<tr><td colspan="6">沒有找到檢舉數據</td></tr>');
//            }
//        },
//        error: function(xhr, status, error) {
//            console.error("載入檢舉數據時出錯:", error);
//            console.log("服務器響應:", xhr.responseText);
//            alert("載入數據失敗，請稍後再試。");
//        }
//    });
//}

////處理檢舉
//function processProsecute(id) {
//    console.log("Processing prosecute with ID:", id);
//    $.ajax({
//        url: `/prosecutes/${id}/process`,
//        type: 'POST',
//        dataType: 'json',
//        success: function(data) {
//            alert("檢舉已成功處理。");
//			
//            deleteProsecuteAfterProcess(id);
//        },
//        error: function(xhr, status, error) {
//            console.error("處理檢舉時出錯:", error);
//            console.log("服務器響應:", xhr.responseText);
//            alert("處理檢舉失敗，請稍後再試。");
//        }
//    });
//}
//
//// 處理完成後刪除檢舉的輔助函數
//function deleteProsecuteAfterProcess(id) {
//    console.log("嘗試刪除已處理的檢舉，ID:", id);
//    $.ajax({
//        url: `/prosecutes/${id}/delete`,
//        type: 'POST',
//        contentType: 'application/json',
//        success: function(data, textStatus, xhr) {
//            console.log('刪除成功，狀態:', textStatus);
//            console.log('響應數據:', data);
//            console.log('狀態碼:', xhr.status);
//            alert("檢舉已處理並成功刪除。");
//            loadProsecutes(); // 重新加載數據
//        },
//        error: function(xhr, status, error) {
//            console.error("刪除已處理檢舉時出錯:");
//            console.error("狀態:", status);
//            console.error("錯誤:", error);
//            console.error("響應文本:", xhr.responseText);
//            console.error("狀態碼:", xhr.status);
//            alert("檢舉已處理，但刪除失敗。請手動刪除。");
//            loadProsecutes(); // 重新加載數據以反映處理結果
//        }
//    });
//}
//
////刪除檢舉
//function deleteProsecute(id) {
//    if (confirm('確定要刪除這條檢舉嗎？')) {
//        console.log("嘗試刪除檢舉，ID:", id);
//        $.ajax({
//            url: `/prosecutes/${id}/delete`,
//            type: 'POST',
//            contentType: 'application/json',
//            beforeSend: function(xhr) {
//                console.log("準備發送刪除請求");
//            },
//            success: function(data, textStatus, xhr) {
//                console.log('刪除成功，狀態:', textStatus);
//                console.log('響應數據:', data);
//                console.log('狀態碼:', xhr.status);
//                alert("檢舉已成功刪除。");
//                loadProsecutes(); // 重新加載數據
//            },
//            error: function(xhr, status, error) {
//                console.error("刪除檢舉時出錯:");
//                console.error("狀態:", status);
//                console.error("錯誤:", error);
//                console.error("響應文本:", xhr.responseText);
//                console.error("狀態碼:", xhr.status);
//                alert("刪除檢舉失敗，請稍後再試。");
//            }
//        });
//    }
//}
//
//function formatDate(dateString) {
//    if (!dateString) return '';
//    const date = new Date(dateString);
//    return date.toLocaleString('zh-TW', { 
//        year: 'numeric', 
//        month: '2-digit', 
//        day: '2-digit',
//        hour: '2-digit', 
//        minute: '2-digit'
//    });
//}


