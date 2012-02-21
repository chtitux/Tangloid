package net.helleboid.tangloid.view;

import net.helleboid.tangloid.activity.AbstractGridActivity;
import net.helleboid.tangloid.model.Letter;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class LetterView extends TextView {
	private AbstractGridActivity context;
	private Letter letter;
	
	public LetterView(AbstractGridActivity context, Letter letter) {
		super(context);
		this.context = context;
		this.letter = letter;
		super.setGravity(Gravity.CENTER);
		super.setTextSize(20);
		
		super.setPadding(4, 4, 4, 4);
		
		super.setText(letter.getLetter());
		
		letter.setView(this);
		
		addListeners();
		
	}
	
	private void addListeners() {
		
		super.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				LetterView.this.context.clickOn(letter);
			}
		});
	}

}
