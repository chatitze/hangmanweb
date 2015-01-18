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
  			table{align:center}
		</style>
	</head>
	<body>
		<div>
    		<h2>Games Status<br></h2>
			<table id="hangmanGamesId" style="width: 60%" align="center" border="1">
              <tr>
       			<td><b> Player </b></td>
       			<td><b> Status </b></td>
       			<td><b> Remaining Moves </b></td>
       			<td><b> Secret Word </b></td>
       			<td><b> Players Guess </b></td>
   			  </tr>			
    		  <c:forEach var="player" items="${playerList}">
	          <tr>
	            <td>${player.userName}</td>
	            <td>${player.gameStatus}</td>
	            <td>${player.game.remainingMoves}</td>
	            <td>${player.game.secretWord}</td>
	            <td><c:forEach var="letter" items="${player.game.visibleLetters}"> ${letter}</c:forEach></td>
	          </tr>
       	     </c:forEach>
			</table>
		</div>
	</body>
</html>
