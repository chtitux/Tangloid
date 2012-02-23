package net.tangloid.activity;

import net.tangloid.R;
import net.tangloid.model.Dictionary;
import net.tangloid.model.Grid;
import net.tangloid.model.Letter;
import net.tangloid.tasks.CheckWordAsyncTask;
import net.tangloid.view.GridView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GridActivity extends AbstractGridActivity {
	
	private Grid grid;
	private ProgressBar progressBar;
	private Button validateButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grid);
        
        grid = new Grid(this, 4, 4);
        
        GridView gridLayout = new GridView(this, grid);

        Button deleteButton = ((Button) findViewById(R.id.layout_grid_deleteButton));
        validateButton = ((Button) findViewById(R.id.layout_grid_validateButton));
        Button restartButton = ((Button) findViewById(R.id.layout_grid_restartButton));
        progressBar = ((ProgressBar) findViewById(R.id.layout_grid_progressBar));
        
        deleteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				grid.deleteLastLetter();
			}
		});
        deleteButton.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				clearWord();
				return true;
			}
		});
        validateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				valideWord();
			}
		});
        restartButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GridActivity.this, GridActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
        
        LinearLayout lLayout = (LinearLayout) findViewById(R.id.layout_grid_gridLinearLayout);
        lLayout.addView(gridLayout);
        
        
    }

	protected void clearWord() {
		while(grid.getCurrentPath().size() > 0) {
			grid.deleteLastLetter();
		}		
	}

	public void clickOn(Letter letter) {
		
		if(grid.isValidNewLetter(letter)) {
			// New letter valid :
			// add to the path and make it clicked
			grid.clickOn(letter);
		} else if(grid.isLastLetter(letter)) {
			this.valideWord();
		} else {
			Toast t = Toast.makeText(this, "Clic invalide!", Toast.LENGTH_SHORT);
			t.show();
		}
		
	}
	private void valideWord() {
		progressBar.setVisibility(View.VISIBLE);
		validateButton.setVisibility(View.GONE);
		CheckWordAsyncTask checkTask = new CheckWordAsyncTask(this);
		checkTask.execute(grid.getCurrentWord());
	}

	@Override
	public void resultForWord(Boolean result) {
		if(result) {
			// Word is OK
			// Add the points and clear the currentPath
			grid.validateWord();
			clearWord();
			Toast t = Toast.makeText(this, "Mot validé !", Toast.LENGTH_SHORT);
			t.show();
		} else {
			// Click invalid and word being validated
			Toast t = Toast.makeText(this, "Mot invalide !", Toast.LENGTH_SHORT);
			t.show();
		}
		
		progressBar.setVisibility(View.GONE);
		validateButton.setVisibility(View.VISIBLE);
		
	}
}
