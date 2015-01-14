<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Hangman Game</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="/hangmangame/resources/js/hangmangame.js"></script>
		<style>
  			body {font-family:verdana}
  			h3   {color:blue}
  			div	 {text-align:center}
		</style>
	</head>
	<body>
		<div>
    		<h2>Hangman Game<br></h2>
    		<h3>Welcome ${currentPlayer.userName}!<br><br></h3>

			<p>Word: 
              <b><span id="visibleLettersId">
                    <c:forEach var="visibleLetters" items="${currentPlayer.hangmanGame.visibleLetters}">
                      ${visibleLetters}
                    </c:forEach>  
                  </span>
               </b>
            </p>
          
            <p>Remaining moves: <b><span id="remainingMovesId">${currentPlayer.hangmanGame.remainingMoves}</span></b></p><br/> 
               
    		<p>
              <c:forEach var="usedLetter" items="${currentPlayer.hangmanGame.usedLetters}">
                <c:if test="${not usedLetter.value}">
                    <button type="button" onclick="sendLetterAjax(this, '${currentPlayer.userName}','${usedLetter.key}')">${usedLetter.key}</button>
                </c:if>
                <c:if test="${usedLetter.value}">
                    <button  disabled="disabled" type="button" >${usedLetter.key}</button>
                </c:if>
              </c:forEach>
    		</p>
    		
    	</div>
    	
    	<div id="notificationId">
          <c:if test="${currentPlayer.isPlayerWonTheGame}">
            <p class="text-success">Congratulations ${currentPlayer.userName}! You won the game!!!</p>
            <button class="btn btn-small btn-primary" type="button" onclick="restartGame('${currentPlayer.userName}')">Restart</button>
          </c:if>
          <c:if test="${currentPlayer.isPlayerLostTheGame}">
            <p class="text-error">You lost the game, the word is: ${currentPlayer.hangmanGame.secretWord} </p>
          	<button class="btn btn-small btn-primary" type="button" onclick="restartGame('${currentPlayer.userName}')">Restart</button>
          </c:if>
        </div>
        
	</body>
</html>
