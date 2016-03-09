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

function renameHeroFunction() {
    var heroId = document.getElementById('selectHero');
    var awsmInput = document.getElementById('identificationName');
    var awsmInputName = document.getElementById('thisHeroName');
    var id = heroId.options[heroId.selectedIndex].value;
    var name = heroId.options[heroId.selectedIndex].text;

    awsmInput.value = id;
    name = name.split(" ");
    var i = 0;
    var newName = "";
    while (i < name.length - 2) {
        if (i > 0)
            newName += " ";
        newName += name[i];
        i++;
    }
    awsmInputName.value = newName;
}

var f = {};

f.disableButtons = function () {
    $('#selectHero').change(function() {
        if ($(this).val() == Number($(this).val())) {
            $('.disabledButton').attr('disabled', false);
            $.ajax({
                url: "/requestHeroDescription?id=" + $(this).val(),
                method: "GET"
            }).then(function(response) {
                $('#displayHeroDescription').val(response);
            })
        }
        console.log($(this));
    })
}

f.removeHero = function () {
    $('#removeOneHero').click(function() {
        var id = $('#selectHero').val();
        $.ajax({
            url: "/deleteHero?id=" + id,
            method: "POST"
        })
    })
}

$(document).ready(function() {
    f.disableButtons();
    f.removeHero();
})