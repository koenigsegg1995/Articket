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