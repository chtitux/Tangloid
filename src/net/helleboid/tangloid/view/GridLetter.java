package net.helleboid.tangloid.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class GridLetter extends TextView {
	private Context context;
	private String letter;
	
	public GridLetter(Context context, String letter) {
		super(context);
		this.context = context;
		this.letter = letter;
		
		super.setText(letter);
	}

}
