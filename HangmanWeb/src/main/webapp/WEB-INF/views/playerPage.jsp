<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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

			<p>
              <span class="label label-info">Word: 
                <b><span id="visibleLettersId">
                      <c:forEach var="visibleLetters" items="${currentPlayer.hangmanGame.visibleLetters}">
                        ${visibleLetters}
                      </c:forEach>  
                    </span>
                 </b>
        	  </span>
            </p>
            <p>
              <span class="label label-info">Remaining moves: <span id="remainingMovesId">${currentPlayer.hangmanGame.remainingMoves}</span></span>
            </p>    
    		<br/>
    		<p>
              <c:forEach var="usedLetter" items="${currentPlayer.hangmanGame.usedLetters}">
                <c:if test="${not usedLetter.value}">
                    <button class="btn btn-small btn-primary" type="button" onclick="sendLetterAjax(this, '${currentPlayer.userName}','${usedLetter.key}')">${usedLetter.key}</button>
                </c:if>
                <c:if test="${usedLetter.value}">
                    <button class="btn btn-small btn-warning" disabled="disabled" type="button" >${usedLetter.key}</button>
                </c:if>
              </c:forEach>
    		</p>
    	</div>
    	
    	<div id="notificationId">
          <c:if test="${currentPlayer.isPlayerWonTheGame}">
            <p class="text-success">Congratulations ${currentPlayer.userName}! You won the game!!!</p>
          </c:if>
          <c:if test="${currentPlayer.isPlayerLostTheGame}">
            <p class="text-error">You lost the game, the word is: ${currentPlayer.hangmanGame.secretWord} </p>
          </c:if>
          <c:if test="${currentPlayer.isPlayerWonTheGame or currentPlayer.isPlayerLostTheGame}">
            <button class="btn btn-small btn-primary" type="button" onclick="restartGame('${currentPlayer.userName}')">Restart</button>
          </c:if>
        </div>
        
	</body>
</html>
