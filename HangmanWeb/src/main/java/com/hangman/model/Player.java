package com.hangman.model;

public class Player {

	private HangmanGame hangmanGame;
	private String 		userName;
	private Boolean 	isPlayerWonTheGame;
	private Boolean 	isPlayerLostTheGame;

	public Player(HangmanGame hangmanGame,String userName, Boolean isPlayerWonTheGame, Boolean isPlayerLostTheGame) {
	    this.hangmanGame 		 = hangmanGame;
	    this.userName 			 = userName;
	    this.isPlayerWonTheGame  = isPlayerWonTheGame;
	    this.isPlayerLostTheGame = isPlayerLostTheGame;
	}
	
	public HangmanGame getHangmanGame() {
	    return hangmanGame;
	}
	
	public void setHangmanGame(HangmanGame hangmanGame) {
	    this.hangmanGame = hangmanGame;
	}
	
	public String getUserName() {
	    return userName;
	}
	
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	
	public Boolean getIsPlayerWonTheGame() {
	    return isPlayerWonTheGame;
	}
	
	public void setIsPlayerWonTheGame(Boolean isPlayerWonTheGame) {
	    this.isPlayerWonTheGame = isPlayerWonTheGame;
	}
	
	public Boolean getIsPlayerLostTheGame() {
	    return isPlayerLostTheGame;
	}
	
	public void setIsPlayerLostTheGame(Boolean isPlayerLostTheGame) {
	    this.isPlayerLostTheGame = isPlayerLostTheGame;
	}

}
