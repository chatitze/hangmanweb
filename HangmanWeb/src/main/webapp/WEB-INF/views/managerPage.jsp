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
    		<h2>Games Status<br></h2>

			<table id="hangmanGamesId" width="50%" align="center" border="1px">
              <tr>
       			<td><b> Player </b></td>
       			<td><b> Status </b></td>
       			<td><b> Remaining Moves </b></td>
       			<td><b> Secret Word </b></td>
   			  </tr>
   			
    		  <c:forEach var="player" items="${playerList}">
	          <tr>
	            <td>
	              <span class="label label-info">${player.userName}</span>
	            </td>
	            <td>
	              <%-- <span class="label label-info">${player.isPlayerWonTheGame}</span> --%>
	              <c:choose>
      				<c:when test="${player.isPlayerWonTheGame}">Won</c:when>
      				<c:when test="${player.isPlayerLostTheGame}">Lost</c:when>
					<c:otherwise>Not finished!</c:otherwise>
				  </c:choose>
	            </td>
	            <td>
	              <span class="label label-info">${player.hangmanGame.remainingMoves}</span>
	            </td>
	            <td>
	              <span class="label label-info">${player.hangmanGame.secretWord}</span>
	            </td>
	          </tr>
       	     </c:forEach>
			</table>
		</div>
	</body>
</html>
