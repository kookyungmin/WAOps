const login = () => {
	let uid = $('#id').val(),
	    upw = $('#password').val();
	let jsonData = {};
	jsonData.uid = uid;
	jsonData.upw = upw;
	if($('#useCookie')[0].checked) {
		jsonData.useCookie = true;
	}
	postToUrl('/loginPost', jsonData);
}