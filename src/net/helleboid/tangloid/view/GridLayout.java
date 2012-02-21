package net.helleboid.tangloid.view;

import net.helleboid.tangloid.model.Grid;
import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GridLayout extends TableLayout {
	private Grid grid;
	private Context context;
	
	public GridLayout(Context context, Grid grid) {
		super(context);
		this.grid = grid;
		this.context = context;
		
		super.setStretchAllColumns(true);
		
		// The table is list of lines,
		// so iteration on lines first
		TableRow tableRow;
		GridLetter gridLetter;
		
		for(int y = 0; y < grid.getHeight(); y++) {
			tableRow = new TableRow(context);
			for(int x = 0; x < grid.getWidth() ; x++) {
				gridLetter = new GridLetter(context, grid.getLetter(x, y));
				tableRow.addView(gridLetter);
			}
			this.addView(tableRow);
		}
	}

}
