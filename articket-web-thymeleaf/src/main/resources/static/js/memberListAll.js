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