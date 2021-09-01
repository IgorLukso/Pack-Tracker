

$(document).ready(function() {
	
	// logout
	$("#logoutLink").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
	});
});


