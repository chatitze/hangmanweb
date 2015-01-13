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
      data: "username=" + userName + "&letter=" + letter,
      dataType: 'json',
      type: 'POST',
      error: function() {
         $('#notificationId').html('<p>An error has occurred!!!</p>');
         $('#notificationId').slideDown('normal').delay(2000).slideUp('fast');
      },
      success: function(currentPlayer) {
         //If the game is finished, nothing is done
		if(!currentPlayer.isFinished){
			//Update the remaining moves count
			$("#remainingMovesId").text(currentPlayer.hangmanGame.remainingMoves);

			//Update the letters of the word to find
			var visibleLetters = "";
			$.each(currentPlayer.hangmanGame.visibleLetters, function (key, value) {
				visibleLetters += (value + " ");
			});
			$("#visibleLettersId").text(visibleLetters);
			
			//Disable the button
			$(button).prop('disabled', true);
			
			//Notification: when the player wins or looses the game
			var notification = "";
			if(currentPlayer.isPlayerWonTheGame){
				notification = '<p class="text-success">Congratulations '+currentPlayer.userName+'! You won the game!!!</p>';
			} else if(currentPlayer.isPlayerLostTheGame){
				notification = '<p class="text-error">You lost the game, the word is: ' + currentPlayer.hangmanGame.secretWord  + '</p>';
			}
			// when the game is finished a restart button is displayed
			if(currentPlayer.isPlayerWonTheGame || currentPlayer.isPlayerLostTheGame){
				notification += "<button class=\"btn btn-small btn-primary\" type=\"button\" onclick=\"restartGame(\'" + userName + "\')\">Restart</button>";
				$("#notificationId").html(notification);
			}
		}   
      }
    });
}