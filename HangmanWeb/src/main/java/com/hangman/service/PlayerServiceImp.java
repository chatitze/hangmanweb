package com.hangman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangman.dao.IGameDao;
import com.hangman.dao.IPlayerDao;
import com.hangman.model.Game;
import com.hangman.model.Player;
import com.hangman.model.Player.GameStatus;
/**
 * @author Chatitze Moumin
 *
 */

@Service
public class PlayerServiceImp implements IPlayerService{

	@Autowired
	private IPlayerDao playerDao;
	
	@Autowired
	private IGameDao gameDao;

	public PlayerServiceImp() {
		super();
	}

	public PlayerServiceImp(IPlayerDao playerDao, IGameDao gameDao){
		this.playerDao = playerDao;
		this.gameDao   = gameDao;
	}
	
	public void setPlayerDao(IPlayerDao playerDao){
		this.playerDao = playerDao;
	}

	public void setGameDao(IGameDao gameDao){
		this.gameDao = gameDao;
	}
	
	@Override
	public List<Player> getAllPlayers() {
		
		return playerDao.getPlayerList();
	}
	
	@Override
	public Player getPlayer(String username) {
		
		return playerDao.getPlayer(username);
	}

	@Override
	public int addPlayer(String username) {
		
		Player player = new Player(username, GameStatus.NOT_FINISHED, null);
		
		Game newGame  = new Game();
		newGame.setPlayer(player);
		player.setGame(newGame);
		
		return playerDao.save(player);
	}

	@Override
	public Player startNewGame(String userName){
		
		Game newGame = new Game();
		Player player = playerDao.getPlayer(userName);
		
		if(player.getGame() != null){
			gameDao.delete(player.getGame().getId());
		}		
		
		player.setGameStatus(GameStatus.NOT_FINISHED);
		player.setGame(newGame);    
		newGame.setPlayer(player);
		playerDao.update(player);
		
		return player;
	}

	@Override
	public void submitLetter(String userName, Character letter){
		Player player = playerDao.getPlayer(userName);
		if(player != null){
			Game currentGame = player.getGame();
			if (currentGame != null ){
				if(currentGame.getSecretWordLetters().indexOf(letter) != -1) {
				    for (int i = 0; i < currentGame.getSecretWordLetters().size(); i++) {
				        if (currentGame.getSecretWordLetters().get(i).equals(letter)) {
				        	currentGame.getVisibleLetters().set(i, letter);
				        }
				     }
				} else {
				    // update remaining moves
				    currentGame.setRemainingMoves(currentGame.getRemainingMoves() - 1);
				}
				
				// add the submitted letter to usedLetters
			    currentGame.getUsedLetters().put(letter, true);

			    //Update the game status of player
			    if(!currentGame.getVisibleLetters().contains('_')){
			    	player.setGameStatus(GameStatus.WON);
			    }else if(currentGame.getVisibleLetters().contains('_') && currentGame.getRemainingMoves() == 0){
			    	player.setGameStatus(GameStatus.LOST);
			    }else{
			    	player.setGameStatus(GameStatus.NOT_FINISHED);
			    }
			    gameDao.update(currentGame);
			    playerDao.update(player);
			}
		}
	}
	
}
