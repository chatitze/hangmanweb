$(document).ready(function() {
	
	// Event for both enabling/disabling the submit (start game) button and pressing the enter key to start the game.
	// Submit button is disabled by default in order to prevent making a request without a username. 
	$('#userName').on("keyup", function(e){
		var userName = $.trim($('#userName').val());
		 // if username is entered
		if(userName.length > 0){
			// if enter key is pressed the game starts
			if(e.keyCode == 13){
				startGame(userName);
			}
			// enable submit button
			$('#submit-button').prop('disabled', false);
		}else{
			// if user name is empty, disable submit button
			$('#submit-button').prop('disabled', true);
		}
	});

});

//Load the game page for requested username 
function startGame(username){
	document.location = '/hangmangame/player?username=' + username;
}

//Reload the page with a new game for the same player
function restartGame(userName) {
	document.location = '/hangmangame/player?username=' + userName + '&restart=true';
}

function sendLetterAjax(button, userName, letter){
	var url = "/hangmangame/player?username=" + userName + "&letter=" + letter;
    $.ajax({
      url: url,
      dataType: 'json',
      type: 'POST',
      error: function() {
         $('#notificationId').html('<p>An error has occurred!!!</p>');
         $('#notificationId').slideDown('normal').delay(2000).slideUp('fast');
      },
      success: function(currentPlayer) {
        //If the game is not finished then update it
		if($("#visibleLettersId:contains(_)")){
			//Update the visible letters
			var visibleLetters = "";
			$.each(currentPlayer.game.visibleLetters, function (key, value) {
				visibleLetters += (value + " ");
			});
			$("#visibleLettersId").text(visibleLetters);
			
			//Update the remaining moves count
			$("#remainingMovesId").text(currentPlayer.game.remainingMoves);

			// when the game is finished a notification and a restart button is displayed
			if(currentPlayer.gameStatus != "NOT_FINISHED"){
				//Notification: when the player wins or looses the game
				var notification = "";
				if(currentPlayer.gameStatus == "WON"){
					notification = '<p>Congratulations '+currentPlayer.userName+'! You won the game!!!</p>';
				} else if(currentPlayer.gameStatus == "LOST"){
					notification = '<p>You lost the game, the word is: ' + currentPlayer.game.secretWord  + '</p>';
				}
				notification += "<button type=\"button\" onclick=\"restartGame(\'" + userName + "\')\">Restart</button>";
				$("#notificationId").html(notification);	
			}
			
			//Disable already submitted letter button
			$(button).prop('disabled', true);
		}   
      }
    });
}
