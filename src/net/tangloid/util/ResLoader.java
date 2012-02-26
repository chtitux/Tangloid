package net.tangloid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;

public class ResLoader {

	public static boolean isWordExists(Context context, String dictionaryFilename,
			String word) {
		try {
			String dicoContent = readFileAsString(context, dictionaryFilename);
			String patternString = "^"+word+"$";
			Pattern pattern = Pattern.compile(patternString, Pattern.UNIX_LINES | Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(dicoContent);
			if(matcher.find()) {
				Log.i("ResLoader-is WordExists", "pattern trouvé");
				return true;
			} else {
				Log.i("ResLoader-is WordExists", "pattern '"+patternString+"' non trouvé dans "+dicoContent.length());

				return false;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;

		}
	}
		
		
/*	public static boolean isWordExists(Context context, String dictionaryFilename,
				String word) {
		try {
			AssetManager am = context.getAssets();
			Log.i("ResLoader-is WordExists", "Fichier dico : " + dictionaryFilename);

			AssetFileDescriptor fd = am.openFd(dictionaryFilename);
			if (fd == null)
				throw new IOException("fd null");

			BufferedReader buf = new BufferedReader(new FileReader(
					fd.getFileDescriptor()));
			String line;

			while ((line = buf.readLine()) != null) {
				if (line.equals(word)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
*/

	public static String readFileAsString(Context context, String filePath)
			throws java.io.IOException {
		Log.v("readAsString", "lecture de "+filePath);
		
		StringBuilder fileData = new StringBuilder();
		
		AssetManager am = context.getAssets();
		InputStream input = am.open(filePath);
		Reader reader = new InputStreamReader(input, "UTF-8");

        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        Log.v("readAsString", "lecture terminée, length "+fileData.length());
        return fileData.toString();
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
