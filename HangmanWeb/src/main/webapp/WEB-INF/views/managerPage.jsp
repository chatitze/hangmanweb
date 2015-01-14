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
			<table id="hangmanGamesId" style="width: 50%" align="center" border="1">
              <tr>
       			<td><b> Player </b></td>
       			<td><b> Status </b></td>
       			<td><b> Remaining Moves </b></td>
       			<td><b> Secret Word </b></td>
   			  </tr>			
    		  <c:forEach var="player" items="${playerList}">
	          <tr>
	            <td>${player.userName}</td>
	            <td>
	              <c:choose>
      				<c:when test="${player.isPlayerWonTheGame}">Won</c:when>
      				<c:when test="${player.isPlayerLostTheGame}">Lost</c:when>
					<c:otherwise>Not finished!</c:otherwise>
				  </c:choose>
	            </td>
	            <td>${player.hangmanGame.remainingMoves}</td>
	            <td>${player.hangmanGame.secretWord}</td>
	          </tr>
       	     </c:forEach>
			</table>
		</div>
	</body>
</html>
