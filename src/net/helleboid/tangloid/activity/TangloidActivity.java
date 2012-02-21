package net.helleboid.tangloid.activity;

import net.helleboid.tangloid.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TangloidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		// Open the select select stop activity
		((Button) findViewById(R.id.button1)).setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				Intent intent = new Intent(TangloidActivity.this, GridActivity.class);
				startActivity(intent);
			}
		});
        
    }
}