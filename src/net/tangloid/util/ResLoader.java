package net.tangloid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;

public class ResLoader {
	
	public static boolean isWordExists(Context context, String language, String word) {
		try {
			AssetManager am = context.getAssets();
			String filename = "words-"+ language + ".png";
			Log.i("Dico", "file : "+filename);

			AssetFileDescriptor fd = am.openFd(filename);
			if(fd == null)
				throw new IOException("fd null");
			
			BufferedReader buf = new BufferedReader(new FileReader(fd.getFileDescriptor()));
			String line;
			
			while((line = buf.readLine()) != null) {
				if(line.equals(word)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	

	/**
	 * @param res
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public static String getDictionary(String language, Context context)
			throws FileNotFoundException, IOException {
		final int BUFFER = 8192;

		InputStream fis = context.getAssets().open("words-" + language+".list");
		;
		if (fis == null)
			return "";

		StringBuilder words = new StringBuilder();
		byte data[] = new byte[BUFFER];
		int count;
		
		while((count = fis.read(data, 0, BUFFER)) != -1) {
			words.append(data);
		}
		
		return words.toString();
		
	}

	/*
	 * 
	 * ZipInputStream zin = new ZipInputStream(new BufferedInputStream(fis,
	 * BUFFER)); ZipEntry entry; while ((entry = zin.getNextEntry()) != null) {
	 * int count;
	 * 
	 * FileOutputStream fos = TestingE3d.mContext.openFileOutput(entry
	 * .getName(), 0); BufferedOutputStream dest = new BufferedOutputStream(fos,
	 * BUFFER);
	 * 
	 * byte data[] = new byte[BUFFER];
	 * 
	 * while ((count = zin.read(data, 0, BUFFER)) != -1) { dest.write(data, 0,
	 * count); // Log.v("NOTAG", "writing "+count + " to "+entry.getName()); }
	 * dest.flush(); dest.close(); } zin.close();
	 * 
	 * }
	 */
}
