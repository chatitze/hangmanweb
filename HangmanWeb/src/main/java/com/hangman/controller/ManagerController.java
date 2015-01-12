package com.hangman.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hangman.model.Player;
import com.hangman.service.PlayerService;

/**
 * @author Chatitze Moumin
 * 
 */

@Controller
public class ManagerController {

	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private PlayerService playerService;
	
	@Resource(name="playerService")
	public void setPlayerService(PlayerService playerService){
		this.playerService = playerService;
	}
	
	/**
	 * GET Manager page
	 * @return manager view to render
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String getAllGames(Model model) {
		logger.info("Manager page!");

		List<Player> playerList = playerService.getAllGamesStatus();
		
        // Set the current games with players as view
        model.addAttribute("playerList", playerList);

		return "managerPage";
	}
}
