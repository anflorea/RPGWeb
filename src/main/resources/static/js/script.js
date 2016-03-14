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
            $('#startMissionButton').attr('href', "/startMission?heroId=" + $(this).val())
            $.ajax({
                url: "/requestHeroDescription?id=" + $(this).val(),
                method: "GET"
            }).then(function(response) {
                $('#displayHeroDescription').val(response);
            })
        }
        //console.log($(this));
    })
}

f.removeHero = function () {
    $('#removeOneHero').click(function() {
        var id = $('#selectHero').val();
        $.ajax({
            url: "/deleteHero?id=" + id,
            method: "DELETE"
        }).then(function(response) {
            $('#' + id).fadeOut(1000);
            $('#displayHeroDescription').val("");
            $('.disabledButton').attr('disabled', true);
        })
    })
}

f.startMission = function() {
    $('#startMissionButton').click(function() {
        var heroId = $('#selectHero').val();
        $.ajax({
            url: "/startMission?heroId=" + heroId,
            method: "GET"
        }).then(function(response) {
            window.location = "/playMission";
        })
    })
}

f.showThatDuck = function() {
    $('.theClass1').html('<img src="img/duck.gif" style="width: 100%; height: auto;"/>');
    $('.theClass0').html('<img src="img/grass.png" style="width: 100%; height: auto;"/>');
    $('.theClass2').html('<img src="img/beholder.gif" style="width: 100%; height:auto;"/>');
}

f.computeProgressBarPercentage = function() {
    var a = $('#currentHealth').text();
    var b = $('#currentExperience').text();
    b = b.substr(b.indexOf('(') + 1, b.indexOf(')') - b.indexOf('(') - 1);
    var a1 = a.substr(a.indexOf('(') + 1, a.indexOf('/') - a.indexOf('(') - 1);
    var a2 = a.substr(a.indexOf('/') + 1, a.indexOf(')') - a.indexOf('/') - 1);
    $('#currentHealthBar').width(100 * a1 / a2 + '%');
    $('#currentExperienceBar').width(b);
}

$(document).ready(function() {
    f.disableButtons();
    f.removeHero();
    f.showThatDuck();
    f.computeProgressBarPercentage();
//    f.startMission();
})