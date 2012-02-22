package net.tangloid.model;

import android.graphics.Color;
import net.tangloid.view.LetterView;

public class Letter {
	
	private String letter;
	private int posx, posy;
	private LetterView view;
	private boolean clicked = false;
	
	public Letter(String letter, int posx, int posy) {
		super();
		this.letter = letter;
		this.posx = posx;
		this.posy = posy;
	}
	public String getLetter() {
		return letter;
	}
	public int getPosx() {
		return posx;
	}
	public int getPosy() {
		return posy;
	}
	public boolean isClicked() {
		return clicked;
	}
	
	public void setView(LetterView letterView) {
		this.view = letterView;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		if(view == null)
			return;
		
		if(clicked) {
			view.setBackgroundColor(Color.RED);
		} else {
			view.setBackgroundColor(Color.TRANSPARENT);
		}
		
	}
	
}
