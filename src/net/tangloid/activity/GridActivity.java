package net.tangloid.activity;

import net.helleboid.tangloid.R;
import net.tangloid.model.Dictionary;
import net.tangloid.model.Grid;
import net.tangloid.model.Letter;
import net.tangloid.view.GridView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GridActivity extends AbstractGridActivity {
	
	private Grid grid;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grid);
        
        grid = new Grid(3, 4);
        
        GridView gridLayout = new GridView(this, grid);
        
        Button deleteButton = ((Button) findViewById(R.id.layout_grid_deleteButton));
        deleteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				grid.deleteLastLetter();
			}
		});
        deleteButton.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				while(grid.getCurrentPath().size() > 0) {
					grid.deleteLastLetter();
				}
				return true;
			}
		});
        
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.layout_grid_gridLinearLayout);
        lLayout.addView(gridLayout);
        
        
    }

	public void clickOn(Letter letter) {
		
		if(grid.isValidNewLetter(letter)) {
			// New letter valid :
			// add to the path and make it clicked
			grid.clickOn(letter);
		} else if(grid.isLastLetter(letter)) {
			if(grid.isValidWord()) {
				// Word is OK
				// Add the points and clear the currentPath
				grid.validateWord();
				Toast t = Toast.makeText(this, "Mot validé !", Toast.LENGTH_SHORT);
				t.show();
			} else {
				// Click invalid and word being validated
				Toast t = Toast.makeText(this, "Mot invalide !", Toast.LENGTH_SHORT);
				t.show();
			}
		} else {
			Toast t = Toast.makeText(this, "Clic invalide!", Toast.LENGTH_SHORT);
			t.show();
		}
		
	}

}
