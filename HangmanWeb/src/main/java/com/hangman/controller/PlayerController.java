package com.hangman.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangman.model.HangmanGame;
import com.hangman.model.Player;
import com.hangman.service.PlayerService;

/**
 * @author Chatitze Moumin
 * 
 */

@Controller
public class PlayerController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	private PlayerService playerService;
	
	@Resource(name="playerService")
	public void setPlayerService(PlayerService playerService){
		this.playerService = playerService;
	}
	
	/**
	 * GET Home Page
	 * @return home view to render
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Welcome to Hangman Game!");
		
		return "welcomePage";
	}
	
	/**
	 * GET Hangman Game page for requested username
	 * @return game view to render
	 */
	@RequestMapping(value = "/player", method = RequestMethod.GET)
	public String submitUsername(Model model,HttpServletRequest request) {
		logger.info("Starting the game!");
		
		//get the username from the request
		String userName= request.getParameter("username");
		
		String restart = request.getParameter("restart");
		  if (StringUtils.isNotEmpty(restart) && Boolean.parseBoolean(restart)) {
			  playerService.removeGame(userName);
        }
		  
		HangmanGame currentGame   = null;
        Player      currentPlayer = null;
        
        // Create or retrieve the game for the user, and set view bean
        if (playerService.isExistingPlayer(userName)) {
      	  currentGame = playerService.getHangmanGame(userName);
      	  boolean isPlayerWonTheGame  = playerService.isPlayerWonTheGame(userName);
      	  boolean isPlayerLostTheGame = playerService.isPlayerLostTheGame(userName);
      	  currentPlayer = new Player(currentGame, userName, isPlayerWonTheGame, isPlayerLostTheGame);
        } else {
      	  currentGame = playerService.startNewGame(userName);
      	  currentPlayer = new Player(currentGame, userName, false, false);
        }

        // Set the current player as a view
        model.addAttribute("currentPlayer", currentPlayer);

		return "playerPage";
	}
	
	/**
	 * POST letter and username
	 * @return game view to render
	 */
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public @ResponseBody Player submitUsernameAndLetter(Model model,HttpServletRequest request, HttpServletResponse response) {

		String userName = request.getParameter("username");
		Character letter = request.getParameter("letter").charAt(0);
  
        // If the game is not finished, the next move will be done
        if (!playerService.isPlayerWonTheGame(userName) && !playerService.isPlayerLostTheGame(userName)) {
        	playerService.submitLetter(userName, letter);
        }

        HangmanGame currentGame = playerService.getHangmanGame(userName);
        Player currentPlayer = new Player(currentGame, userName, playerService.isPlayerWonTheGame(userName), playerService.isPlayerLostTheGame(userName));
        return currentPlayer;        
	}
}
