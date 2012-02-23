package net.tangloid.tasks;

import net.tangloid.activity.AbstractGridActivity;
import net.tangloid.util.ResLoader;
import android.content.Context;
import android.os.AsyncTask;

public class CheckWordAsyncTask extends AsyncTask<String, Integer, Boolean> {

	private AbstractGridActivity context;
	
	public CheckWordAsyncTask(AbstractGridActivity context) {
		this.context = context;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		if(params == null || params.length == 0)
			return false;
		
		return ResLoader.isWordExists(context, "fr", params[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		
		context.resultForWord(result);
		super.onPostExecute(result);
	}

}
