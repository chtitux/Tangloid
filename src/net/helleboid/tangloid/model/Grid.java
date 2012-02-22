package net.helleboid.tangloid.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;


public class Grid {

	private Letter[][] letters;
	private int width, height;
	private String dico;
	
	private List<Letter> currentPath;
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		this.dico = "ABCDED";
		this.letters = new Letter[width][height];
		this.currentPath = new ArrayList<Letter>();
		
		generateNewGrid();
	}
	

	public void generateNewGrid() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				letters[x][y] = new Letter(x+","+y, x, y);
			}
		}
	}
	
	public boolean isWordExists(String word) {
		return (dico.indexOf(word) > -1);
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
		// TODO Auto-generated method stub
		return true ;
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
