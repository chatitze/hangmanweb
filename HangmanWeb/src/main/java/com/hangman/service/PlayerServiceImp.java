package com.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.hangman.model.HangmanGame;
import com.hangman.model.Player;

@Service
public class PlayerServiceImp implements PlayerService{

	private final Map<String, HangmanGame> hangmanGames = new ConcurrentHashMap<String, HangmanGame>();

	public PlayerServiceImp() {
		super();
	}

	@Override
	public HangmanGame startNewGame(String userName){
		HangmanGame newGame = new HangmanGame();
		hangmanGames.put(userName, newGame);
	    return newGame;
	}

	@Override
	public void submitLetter(String userName, Character letter){
		HangmanGame currentGame = getHangmanGame(userName);
	    if (currentGame.getSecretWordLetters().indexOf(letter) != -1) {
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
	}

	@Override
	public HangmanGame getHangmanGame(String userName){
		return hangmanGames.get(userName);
	}

	@Override
	public void removeGame(String userName) {
		hangmanGames.remove(userName);
	}
	
	@Override
	public boolean isExistingPlayer(String userName){
		return hangmanGames.containsKey(userName);
	}

	@Override
	public boolean isLetterAlreadySubmitted(String userName, Character letter) {
		HangmanGame currentGame = hangmanGames.get(userName);
	    if (currentGame.getVisibleLetters().indexOf(letter) != -1 || currentGame.getUsedLetters().get(letter)) {
	      return true;
	    }
	    return false;
	}

	@Override
	public boolean isPlayerWonTheGame(String userName) {
		HangmanGame currentGame = hangmanGames.get(userName);
	    return (!currentGame.getVisibleLetters().contains('_'));
	}

	@Override
	public boolean isPlayerLostTheGame(String userName) {
		HangmanGame currentGame = hangmanGames.get(userName);
	    return (currentGame.getVisibleLetters().contains('_') && currentGame.getRemainingMoves() == 0);
	}

	@Override
	public List<Player> getAllGamesStatus() {
		
		List<Player> playerList = new ArrayList<Player>();
		
		Player player = null;
		
		for (String key : hangmanGames.keySet()) {
			
			boolean isPlayerWon  = isPlayerWonTheGame(key);
			boolean isPlayerLost = isPlayerLostTheGame(key);

			player = new Player(hangmanGames.get(key), key, isPlayerWon, isPlayerLost);
			playerList.add(player);
		}
		
		return playerList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
