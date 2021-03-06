package net.tangloid.view;

import net.tangloid.activity.AbstractGridActivity;
import net.tangloid.model.Letter;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class LetterView extends TextView {
	private static final int PADDING = 35;
	
	private AbstractGridActivity context;
	private Letter letter;
	
	public LetterView(AbstractGridActivity context, Letter letter) {
		super(context);
		this.context = context;
		this.letter = letter;
		super.setGravity(Gravity.CENTER);
		super.setTextSize(20);
		
		super.setPadding(PADDING, PADDING, PADDING, PADDING);
		
		super.setText(letter.getLetter());
		super.setTag(letter);
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
