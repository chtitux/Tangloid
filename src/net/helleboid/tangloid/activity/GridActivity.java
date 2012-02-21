package net.helleboid.tangloid.activity;

import java.util.ArrayList;
import java.util.List;

import net.helleboid.tangloid.R;
import net.helleboid.tangloid.model.Grid;
import net.helleboid.tangloid.model.Letter;
import net.helleboid.tangloid.view.GridView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class GridActivity extends AbstractGridActivity {
	
	private Grid grid;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grid);
        
        grid = new Grid(3, 4);
        
        GridView gridLayout = new GridView(this, grid);
        
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.layout_grid_gridLinearLayout);
        lLayout.addView(gridLayout);
        
        
    }

	public void clickOn(Letter letter) {
		
		if(grid.isValidNewLetter(letter)) {
			// New letter valid :
			// add to the path and make it clicked
			grid.clickOn(letter);
		} else {
			// Click invalid
			if(letter.isClicked() && grid.isValidWord()) {
				// Word is OK
				// Add the points and clear the currentPath
				grid.validateWord();
				
			} else {
				// Click invalid and word being validated
			}
		}
		
	}

}
