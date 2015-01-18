package com.hangman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hangman.utils.HangmanGameUtils;

/**
 * @author Chatitze Moumin
 *
 */

@Entity
@Table(name = "game", catalog = "hangmangame")
public class Game implements Serializable{

	private static final long serialVersionUID = -6801965386969996581L;

	
	private int id;
	
	private int remainingMoves = 7;
	
	private String secretWord;

	private Player player;
	
	private List<Character> visibleLetters    = new ArrayList<Character>();
	
	private List<Character> secretWordLetters = new ArrayList<Character>();

    private Map<Character,Boolean> usedLetters = new HashMap<Character,Boolean>();
    
    /**
     * Initialise the secretWordLetters from secretWord 
     * and the visibleLetters with '_' characters. 
     */
	public Game(){
		super();
	    this.secretWord = StringUtils.upperCase(HangmanGameUtils.selectRandomWord());
	    int secretWordLegth = this.secretWord.length();
	    int letterListSize = HangmanGameUtils.letterList.size();
	    
	    // set all the letters in alphabet and initialise them false (not used yet) in the beginning of each new game
	    for(int i = 0; i < letterListSize; i++){
	    	usedLetters.put(HangmanGameUtils.letterList.get(i), false);
	    }
	    
	    for (int i = 0; i < secretWordLegth; i++) {
	      secretWordLetters.add(this.secretWord.charAt(i));
	      visibleLetters.add('_');
	    }  
	    
	}

	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "player"))
	@GeneratedValue(generator = "generator")
	@Column(name = "GAME_ID", unique = true, nullable = false)
	@Id
	public int getId() {
	    return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}

	@Column(name = "SECRET_WORD", nullable = false)
	public String getSecretWord() {
	   return this.secretWord;
	}
	
	public void setSecretWord(String secretWord){
		this.secretWord = secretWord;
	}
	
	@Column(name = "REMAINING_MOVES")  
	public int getRemainingMoves() {
		return this.remainingMoves;
	}

	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	@JsonBackReference
	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@Column(name="SECRETWORD_LETTERS")
    @ElementCollection(targetClass=Character.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Character> getSecretWordLetters() {
	    return this.secretWordLetters;
	}
	
	public void setSecretWordLetters(List<Character> secretWordLetters){
		this.secretWordLetters = secretWordLetters;
	}
	
	@Column(name="VISIBLE_LETTERS")
    @ElementCollection(targetClass=Character.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Character> getVisibleLetters() {
	    return this.visibleLetters;
	}
	
	public void setVisibleLetters(List<Character> visibleLetters){
		this.visibleLetters = visibleLetters;
	}
	
	@Column(name="USED_LETTERS")
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	public Map<Character, Boolean> getUsedLetters() {
	    return this.usedLetters;
	}

	public void setUsedLetters(Map<Character, Boolean> usedLetters){
		this.usedLetters = usedLetters;
	}
}
