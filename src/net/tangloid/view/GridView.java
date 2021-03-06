package net.tangloid.view;

import net.tangloid.activity.AbstractGridActivity;
import net.tangloid.model.Grid;
import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GridView extends TableLayout {
	private Grid grid;
	private Context context;
	
	public GridView(AbstractGridActivity context, Grid grid) {
		super(context);
		this.grid = grid;
		this.context = context;
				
		// The table is list of lines,
		// so iteration on lines first
		TableRow tableRow;
		LetterView gridLetter;
		
		for(int y = 0; y < grid.getHeight(); y++) {
			tableRow = new TableRow(context);
			tableRow.setGravity(Gravity.CENTER);
			for(int x = 0; x < grid.getWidth() ; x++) {
				gridLetter = new LetterView(context, grid.getLetter(x, y));
				tableRow.addView(gridLetter);
			}
			this.addView(tableRow);
		}
	}

}
