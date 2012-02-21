package net.helleboid.tangloid.activity;

import net.helleboid.tangloid.R;
import net.helleboid.tangloid.model.Grid;
import net.helleboid.tangloid.view.GridLayout;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ActivityGrid extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grid);
        
        Grid grid = new Grid(3, 4);
        
        GridLayout gridLayout = new GridLayout(this, grid);
        
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.layout_grid_gridLinearLayout);
        lLayout.addView(gridLayout);
        
    }

}
