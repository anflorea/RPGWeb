function showLoginPane (isVisible) {

    var url = window.location.href;
	var element = document.getElementById('loginPaneId');
	var largeDiv = document.getElementById('largeLoginPaneId');

    var loginFailed = url.indexOf("?failed=true");

    if (loginFailed != -1)
        loginFailed = true;
    else
        loginFailed = false;

    if (isVisible == false) {
		element.className = 'loginPane';
		largeDiv.className = 'largeLoginPane';
	}
	else if (isVisible == true || loginFailed == true) {
		element.className += ' showLoginPane';
		largeDiv.className += ' showLoginPane';
	}
	else {
		element.className = 'loginPane';
		largeDiv.className = 'largeLoginPane';
	}
}