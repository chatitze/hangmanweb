package com.hangman.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * @author Chatitze Moumin
 *
 */
@Entity
@Table(name = "player", catalog = "hangmangame", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USERNAME") })
public class Player implements Serializable{

	private static final long serialVersionUID = 5395798340655882970L;

	public enum GameStatus{
		NOT_FINISHED, WON, LOST 
	};

	private int id;
	private String 	userName;
	private GameStatus gameStatus;
	private Game game;

	public Player(){
		super();
	};
	
	public Player(String userName, GameStatus gameStatus, Game game) {
		super();
	    this.game = game;
	    this.userName 	= userName;
	    this.gameStatus  = gameStatus;
	}
	
	@Id
	@Column(name = "PLAYER_ID", unique = true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
	    return this.id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	
	
	@Column(name = "USERNAME", unique = true, nullable = false)
	public String getUserName() {
	    return this.userName;
	}
	
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	
	@Column(name = "GAME_STATUS")
	@Enumerated(EnumType.STRING)
	public GameStatus getGameStatus() {
	    return this.gameStatus;
	}
	
	public void setGameStatus(GameStatus gameStatus) {
	    this.gameStatus = gameStatus;
	}
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "player", cascade = CascadeType.ALL)
	@JsonManagedReference
	public Game getGame() {
	    return game;
	}
	
	public void setGame(Game game) {
	    this.game = game;
	}
}
