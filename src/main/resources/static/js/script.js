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
            method: "POST"
        }).then(function(response) {
            if (response == true)
                window.location = "/playMission";
        })
    })
}

f.showThatDuck = function() {
    $('.theClass1').html('<img src="img/duck.gif" style="width: 100%; height: auto;"/>');
    $('.theClass0').html('<img src="img/grass.png" style="width: 100%; height: auto;"/>');
    $('.theClass2').html('<img src="img/grass.png" style="width: 100%; height:auto;"/>');
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

f.moveTheHero = function() {
    $('#buttonMoveUp').click(function () {
        $.ajax({
            url: "/moveHero?move=UP",
            method: "POST"
        }).then(function (response) {
            f.alertmove(response);
        })
    })
    $('#buttonMoveLeft').click(function () {
        $.ajax({
            url: "/moveHero?move=LEFT",
            method: "POST"
        }).then(function (response) {
            f.alertmove(response);
        })
    })
    $('#buttonMoveDown').click(function () {
        $.ajax({
            url: "/moveHero?move=DOWN",
            method: "POST"
        }).then(function (response) {
            f.alertmove(response);
        })
    })
    $('#buttonMoveRight').click(function () {
        $.ajax({
            url: "/moveHero?move=RIGHT",
            method: "POST"
        }).then(function (response) {
            f.alertmove(response);
        })
    })
}

f.alertmove = function(response) {
    console.log(response);
    if (response == "OK")
        window.location = "/playMission";
    if (response.substr(0,7) == "monster") {
        var txt;

        $('#fightTheMonsterLabel').text("You have encountered a monster. " + response.substr(7,response.length) + "This monsta' is sehr dangerous!\nWanna fight it? ;)");
        $('#fightTheMonster').modal('show');

        //var r =  confirm("You have encountered a monster. " + response.substr(7,response.length) + "This monsta' is sehr dangerous!\nWanna fight it? ;)");
//        if (r == true) {
//           alert("You chose to fight for saving the World!!!");
//        } else {
//           alert("Is not so bad to be a coward :-D")
//        }
//        //confirm("You have encountered a monster. " + response.substr(7,response.length) + "This monsta' is sehr dangerous!\nWanna fight it? ;)");


    }
    console.log(response);
    if (response == "WIN") {
        alert("You fucking WON, m8!");
        $.ajax({
            url: "/winLevel",
            method: "GET"
        }).then(function(response) {
            window.location = "/selectHero";
        })
    }
}

f.encounterHero = function() {
    $('#buttonFightMonster').click(function() {
        $.ajax({
            url: "/fightMonster",
            method: "POST"
        }).then(function(response) {
            $('#fightTheMonster').modal('hide');
            if (response == "monsterDefeated") {
                alert("You have defeated the monster! yaaay, Now go celebrate.");
                window.location = window.location;
            }
            else {
                alert("You died :(");
                window.location = "/selectHero";
            }
        })
    })
    $('#buttonRunMonster').click(function() {
        $.ajax({
            url: "/runMonster",
            method: "POST"
        }).then(function(response) {
            if (response == "run") {
                alert("You escaped from the monster");
                window.location = "/playMission";
            }
            else if (response == "monsterDefeated") {
                alert("You couldn't escape from the monster, but you defeated it like a brave warrior!");
                window.location = window.location;
            }
            else {
                alert("You couldn't escape from the monster, and you died :(");
                window.location = "/selectHero";
            }
        })
    })
}

$(document).ready(function() {
    f.disableButtons();
    f.removeHero();
    f.showThatDuck();
    f.computeProgressBarPercentage();
    f.startMission();
    f.moveTheHero();
    f.encounterHero();
})