package net.tangloid.activity;

import java.util.Timer;
import java.util.TimerTask;

import net.tangloid.R;
import net.tangloid.model.Dictionary;
import net.tangloid.model.Grid;
import net.tangloid.model.Letter;
import net.tangloid.tasks.CheckWordAsyncTask;
import net.tangloid.view.GridView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GridActivity extends AbstractGridActivity {
	
	private Grid grid;
	private ProgressBar searchProgressBar;
	private Button validateButton;
	private ProgressBar timeProgressBar;
	private Integer remainingTime;
	private Button restartButton;
	private Button deleteButton;
	private Timer timer;
	private boolean currentlySeekingWord = false;
	private TextView timeTextView;
	private Handler mHandler = new Handler();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grid);
        
        grid = new Grid(this, 4, 4);
        
        GridView gridLayout = new GridView(this, grid);
        remainingTime = 30;

        deleteButton = ((Button) findViewById(R.id.layout_grid_deleteButton));
        validateButton = ((Button) findViewById(R.id.layout_grid_validateButton));
        restartButton = ((Button) findViewById(R.id.layout_grid_restartButton));
        searchProgressBar = ((ProgressBar) findViewById(R.id.layout_grid_searchProgressBar));
        timeProgressBar = ((ProgressBar) findViewById(R.id.layout_grid_timeProgressBar));
        timeProgressBar.setMax(120); // Max to 2 minutes
        timeTextView = ((TextView) findViewById(R.id.layout_grid_timeTextView));

        
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
        
        timer = new Timer();
        timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(!currentlySeekingWord) {
					mHandler.post(new Runnable() {
						public void run() {
							updateRemainingTime(-1);
						}
					});
				}
			}
		}, 100, 1000);
        
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
		searchProgressBar.setVisibility(View.VISIBLE);
		validateButton.setVisibility(View.GONE);
		CheckWordAsyncTask checkTask = new CheckWordAsyncTask(this);
		currentlySeekingWord = true;
		checkTask.execute(grid.getCurrentWord());
	}

	@Override
	public void resultForWord(Boolean result) {
		currentlySeekingWord = false;
		if(result) {
			// Word is OK
			// Add the points and clear the currentPath
			remainingTime =+ grid.getCurrentWord().length()*2;
			grid.validateWord();
			clearWord();
			Toast t = Toast.makeText(this, "Mot validé !", Toast.LENGTH_SHORT);
			t.show();
		} else {
			// Click invalid and word being validated
			Toast t = Toast.makeText(this, "Mot invalide !", Toast.LENGTH_SHORT);
			t.show();
		}
		
		searchProgressBar.setVisibility(View.GONE);
		validateButton.setVisibility(View.VISIBLE);
		
	}
	
	public void updateRemainingTime(Integer offset) {
		remainingTime += offset;
//		
//		mHandler.post(new Runnable() {
//			public void run() {
				timeProgressBar.setProgress(remainingTime);
				timeTextView.setText(((int) remainingTime / 60) + ":"+ (remainingTime % 60)); 
				
//			}
//		});
		if(remainingTime == 0) {
			timer.cancel();
//			mHandler.post(new Runnable() {
//				public void run() {
					Toast t = Toast.makeText(GridActivity.this, "Perdu !", Toast.LENGTH_LONG);
					t.show();
//				}
//			});
		}
	}
}
