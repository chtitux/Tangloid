package net.tangloid.activity;

import android.app.Activity;
import net.tangloid.model.Letter;

public abstract class AbstractGridActivity extends Activity {
	
	public abstract void clickOn(Letter letter);

	public abstract void resultForWord(Boolean result);

}
