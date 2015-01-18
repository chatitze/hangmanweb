package com.hangman.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangman.model.Player;
import com.hangman.model.Player.GameStatus;
import com.hangman.service.IPlayerService;

/**
 * @author Chatitze Moumin
 * 
 */

@Controller
public class PlayerController {
	
	@Autowired
	private IPlayerService playerService;
	
	@Resource(name="playerService")
	public void setPlayerService(IPlayerService playerService){
		this.playerService = playerService;
	}
	
	/**
	 * GET Home Page
	 * @return home view to render
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "welcomePage";
	}
	
	/**
	 * GET Hangman Game page for requested username
	 * @return game view to render
	 */
	@RequestMapping(value = "/player", method = RequestMethod.GET)
	public String submitUsername(Model model,HttpServletRequest request) {
		
		//get the username and restart parameters from the request
		String userName= request.getParameter("username");
		String restart = request.getParameter("restart");

		Player currentPlayer = playerService.getPlayer(userName);

		if(currentPlayer == null){
        	// There is no Player found for given username, add a new player
        	playerService.addPlayer(userName);
        	currentPlayer = playerService.getPlayer(userName);
        	
		}else if((StringUtils.isNotEmpty(restart) && Boolean.parseBoolean(restart)) || currentPlayer.getGame() == null){
        	// There is no game started yet, or restart the game requested for username, start a new game
        	playerService.startNewGame(userName);
        	currentPlayer = playerService.getPlayer(userName);
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
  
		Player currentPlayer = playerService.getPlayer(userName);

        if(GameStatus.NOT_FINISHED.equals(currentPlayer.getGameStatus())){
        	// The game is not finished, do the next move
        	playerService.submitLetter(userName, letter);
        	currentPlayer = playerService.getPlayer(userName);
        }
        
        return currentPlayer;
	}
}
