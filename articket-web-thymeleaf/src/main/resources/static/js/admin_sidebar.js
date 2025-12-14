document.addEventListener('DOMContentLoaded', function() {
    const sidebarPlaceholder = document.getElementById('sidebar-placeholder'); 
    
    if (sidebarPlaceholder) {

        if (sidebarPlaceholder.children.length > 0) {
            initializeSidebar();
        } else {
            const observer = new MutationObserver(function(mutations) {
                if (sidebarPlaceholder.children.length > 0) {
                    initializeSidebar();
                    observer.disconnect();
                }
            });
            observer.observe(sidebarPlaceholder, {childList: true});
        }
    } else {
        console.error('Sidebar placeholder not found');
    }
});

function initializeSidebar() {

    const mainMenuItems = document.querySelectorAll('.nav-link.main-menu');

    mainMenuItems.forEach((item, index) => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('data-target');
            const targetSubmenu = document.getElementById(targetId);

            // Close all submenus except the target
            document.querySelectorAll('.submenu').forEach(submenu => {
                if (submenu.id !== targetId) {
                    submenu.classList.remove('active');
                   
                }
            });
            
            // Toggle the target submenu
            if (targetSubmenu) {
                targetSubmenu.classList.toggle('active');
                
            } else {
                console.error(`Submenu with id ${targetId} not found`);
            }
        });
    });

    const navLinks = document.querySelectorAll('.nav-link[data-section]');

    navLinks.forEach((link, index) => {
        link.addEventListener('click', function(e) {
            const targetSection = this.getAttribute('data-section');
            showSection(targetSection);
        });
    });
}

function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');

    sections.forEach(section => {
        section.style.display = 'none';
    });

    const targetSection = document.getElementById(sectionId);
    if (targetSection) {
        targetSection.style.display = 'block';
    } else {
        console.error(`Section with id ${sectionId} not found`);
    }
}