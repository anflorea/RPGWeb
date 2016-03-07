function showLoginPane (isVisible) {

	var element = document.getElementById('loginPaneId');
	var largeDiv = document.getElementById('largeLoginPaneId');

	if (isVisible == true) {
		element.className += ' showLoginPane';
		largeDiv.className += ' showLoginPane';
	}
	else {
		element.className = 'loginPane';
		largeDiv.className = 'largeLoginPane';
	}
}