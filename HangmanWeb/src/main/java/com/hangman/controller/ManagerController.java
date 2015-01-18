package com.hangman.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hangman.model.Player;
import com.hangman.service.IPlayerService;

/**
 * @author Chatitze Moumin
 * 
 */

@Controller
public class ManagerController {

	@Autowired
	private IPlayerService playerService;
	
	@Resource(name="playerService")
	public void setPlayerService(IPlayerService playerService){
		this.playerService = playerService;
	}
	
	/**
	 * GET Manager page
	 * @return manager view to render
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String getAllGames(Model model) {
		
		List<Player> playerList = playerService.getAllPlayers();
		
        // Set the current games with players as view
        model.addAttribute("playerList", playerList);

		return "managerPage";
	}
}
