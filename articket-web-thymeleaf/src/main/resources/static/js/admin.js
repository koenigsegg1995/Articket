document.addEventListener('DOMContentLoaded', function() {
    const sidebarPlaceholder = document.getElementById('sidebar-placeholder');
    if (sidebarPlaceholder) {
        fetch('/adminSidebar')
            .then(response => response.text())
            .then(data => {
                console.log('Received data:', data);
                sidebarPlaceholder.innerHTML = data;
                bindNavLinks(); // 在這裡綁定 navLinks 事件
            })
            .catch(error => console.error('Error loading sidebar:', error));
    } else {
        console.error('Sidebar placeholder element not found');
    }
});

function bindNavLinks() {
    var navLinks = document.querySelectorAll('.nav-link[data-section]');
    var sections = document.querySelectorAll('.section');
    var submenuLinks = document.querySelectorAll('.nav-link[data-toggle="submenu"]');

    navLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
//            event.preventDefault();

            // 隱藏所有 section
            sections.forEach(function(section) {
                section.classList.remove('active');
            });

            // 取消所有 nav-link 的 active 狀態
            navLinks.forEach(function(navLink) {
                navLink.classList.remove('active');
            });

            // 顯示對應的 section
            var targetSection = document.getElementById(this.getAttribute('data-section'));
            targetSection.classList.add('active');

            // 設置當前 nav-link 為 active
            this.classList.add('active');
        });
    });

    submenuLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();

            // 切換子選單顯示
            var targetSubmenu = document.getElementById(this.getAttribute('data-target'));
            targetSubmenu.style.display = targetSubmenu.style.display === 'block' ? 'none' : 'block';
        });
    });

    // 預設顯示第一個 section
    if (sections.length > 0) {
        sections[0].classList.add('active');
    }
}
