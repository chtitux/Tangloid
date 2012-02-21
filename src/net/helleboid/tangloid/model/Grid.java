package net.helleboid.tangloid.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager.AssetInputStream;

public class Grid {

	private String[][] letters;
	private int width, height;
	private String dico;
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		this.dico = "ABCDED";

	}
	

	public void generateNewGrid() {
		letters = new String[width][height];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				letters[x][y] = x+","+y;
			}
		}
	}
	
	public boolean isWordExists(String word) {
		return (dico.indexOf(word) > -1);
	}
}
