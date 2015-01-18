The challenge is to build a simple version of a 'hangman' game as a web-app (look & feel is not important).
- The app should use jQuery and Ajax queries for interaction.
- The app should keep the current game state persistent across server and browser re-starts.
- The app should be built with 'ant' and produce a war file that can be deployed in a Tomcat instance.
- The app should have a 'management' page that shows a summary of the state of all games that are currently being played.


# My HangmanWeb project

The project was tested in Chrome, Firefox, and IE 11 browsers.
I used maven as a build tool. 
The war file "hangmangame-1.0.0-BUILD-SNAPSHOT.war" will be generated under the target folder by running "mvn clean package" from the command line.
Then the war file can be deployed and run on Tomcat server. 

I used MySql database to store the player and game details.
There is a 'HahgmanGameDB.txt file added to the project folder that includes the database scripts.
In my local the port for MySql is 3306, and both username and password is 'root'. 
These MySql definitions can be changed in class 'ApplicationContextConfig' under'com.hangman.spring.config' package.

From the client point of view, there are three different pages:

1. When the user hits "http://localhost:8080/hangmangame/"
It brings the welcome page that asks to enter a username.

2. After user enters a username and press enter or submit button, it brings the game page and the link gets changes as 
e.g. if the username is "chatitze" --> "http://localhost:8080/hangmangame/player?username=chatitze"

Now it is the game page with a secret word where each character is displayed with underscore.
Meanwhile, the remaining moves is displayed right after the secret word.
User can choose any letter to guess the secret word by clicking the letter button.
Each time when the user clicks a letter button an Ajax request will be sent to the server, including the letter and the username.

3. The last page is the manangement page of all games that are currently being played. 
The link is: "http://localhost:8080/hangmangame/manager"