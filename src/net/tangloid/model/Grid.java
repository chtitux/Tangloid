package net.tangloid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import android.os.Bundle;


public class Grid {

	private Letter[][] letters;
	private int width, height;
	private Dictionary dico;
	
	private List<Letter> currentPath;
	private LinkedHashMap<Integer, String> repartition;
	private Random random;
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		this.dico = new Dictionary();

		this.letters = new Letter[width][height];
		this.currentPath = new ArrayList<Letter>();
		this.repartition = new LinkedHashMap<Integer, String>();
		this.random = new Random();
		// See doc/repartition.xls , data from http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d'apparition_des_lettres_en_fran%C3%A7ais
		repartition.put(16, "E");
		repartition.put(24, "S");
		repartition.put(32, "A");
		repartition.put(39, "I");
		repartition.put(46, "T");
		repartition.put(54, "N");
		repartition.put(60, "R");
		repartition.put(66, "U");
		repartition.put(72, "L");
		repartition.put(77, "O");
		repartition.put(81, "D");
		repartition.put(84, "C");
		repartition.put(87, "P");
		repartition.put(90, "M");
		repartition.put(91, "V");
		repartition.put(92, "QU");
		repartition.put(93, "F");
		repartition.put(94, "B");
		repartition.put(95, "G");
		repartition.put(96, "H");
		repartition.put(97, "J");
		repartition.put(98, "X");
		repartition.put(99, "Y");
		repartition.put(100, "Z");
		repartition.put(101, "W");
		repartition.put(102, "K");
		
		generateNewGrid();
	}
	

	public void generateNewGrid() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				letters[x][y] = new Letter(this.getNewLetter(), x, y);
			}
		}
	}

	protected String getNewLetter() {
		int r = random.nextInt(103);
		for(Map.Entry<Integer, String> entry : repartition.entrySet()) {
			if(r <= entry.getKey()) {
				//System.out.println(((Integer) repartition.elementAt(i+1)).intValue()+"< "+random );
				return (String) entry.getValue();
			}
		}
		return "#";
	}


	public Letter[][] getLetters() {
		return letters;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public Letter getLetter(int x, int y) {
		return letters[x][y];
	}


	public boolean isValidNewLetter(Letter letter) {
		// The letter must be not-cliked and its position not more far than 1
		if(letter.isClicked()) {
			return false;
		}
		// If the current path is empty, the click is OK
		if(currentPath.size() == 0)
			return true;
		
		Letter lastLetter = currentPath.get(currentPath.size() - 1);
		if(Math.abs(letter.getPosx() - lastLetter.getPosx()) <= 1
			&& 
			Math.abs(letter.getPosy() - lastLetter.getPosy()) <= 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean isValidWord() {
		return isValidWord(getCurrentWord());
	}
	
	private String getCurrentWord() {
		StringBuilder word = new StringBuilder();
		for(Letter letter : currentPath) {
			word.append(letter.getLetter());
		}
		return word.toString()
				;
	}


	public boolean isValidWord(String word) {
		return dico.isValidWord(word);
	}


	public void validateWord() {
		// TODO Auto-generated method stub
		
	}


	public void clickOn(Letter letter) {
		currentPath.add(letter);
		letter.setClicked(true);
		
	}


	public boolean isLastLetter(Letter letter) {
		if(currentPath.size() == 0)
			return false;
		
		return currentPath.get(currentPath.size() - 1).equals(letter);
	}


	public void deleteLastLetter() {
		if(currentPath.size() == 0)
			return;
		
		Letter lastLetter = currentPath.remove(currentPath.size() - 1);
		lastLetter.setClicked(false);
	}


	public List<Letter> getCurrentPath() {
		return currentPath;
	}
}
