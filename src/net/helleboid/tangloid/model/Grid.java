package net.helleboid.tangloid.model;


public class Grid {

	private Letter[][] letters;
	private int width, height;
	private String dico;
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		this.dico = "ABCDED";
		this.letters = new Letter[width][height];
		
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
}
