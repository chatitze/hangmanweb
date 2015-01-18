<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> --%>
<html>
	<head>
		<title>Hangman Game</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="/hangmangame/resources/js/hangmangame.js"></script>		
	</head>
	<body>
		<div style="text-align:center">
	    	<h2 style="font-family:verdana"><br>Welcome to Hangman Game<br></h2>		
			<label style="font-family:verdana"><br>Please enter a username: </label>
			<input name="userName" id="userName"/>
	    	<button id="submit-button" onclick="startGame($.trim($('#userName').val()))" disabled>submit</button>	    	
		</div>
	</body>
</html>
