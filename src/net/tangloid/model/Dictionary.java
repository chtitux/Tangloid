package net.tangloid.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import net.tangloid.util.ResLoader;

public class Dictionary {
	
	private static Dictionary instance;
	private LinkedHashMap<String, String> lastWords;
	
	public static final String DICO_PATH = "dico/";
	
	private Dictionary(Context context) {
		this.lastWords = new LinkedHashMap<String,String>();
		
		String filename = DICO_PATH + "tree.json.png";		
		Log.i("Dico", "Fichier JSON tree : "+filename);

		try {
			String jsonString = ResLoader.readFileAsString(context, filename);
			JSONObject jsonTree = new JSONObject(jsonString);

			JSONArray jsonDico = jsonTree.getJSONArray("dico");
			
			JSONObject line;
			for(int i = 0; i < jsonDico.length(); i++) {
				line = jsonDico.getJSONObject(i);
				lastWords.put(line.getString("stop"), line.getString("file"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	
	public static Dictionary getInstance(Context c) {
		if(instance == null) {
			instance = new Dictionary(c);
		}
		return instance;
	}
	
	public boolean isValidWord(Context context, String testedWord) {
		String dicoFilename = chooseDico(testedWord);
		return ResLoader.isWordExists(context, DICO_PATH + dicoFilename, testedWord);
	}
	
	
	/**
	 * Return the filename of the dico
	 * @param word
	 * @return
	 */
	public String chooseDico(String word) {
		for(Entry<String, String> line : this.lastWords.entrySet() ) {
			if(isAfter(word, line.getKey())) {
				Log.i("Dico/chooseDico", word+" is after "+line.getKey());
				continue;
			} else {
				Log.i("Dico/chooseDico", word+" is before "+line.getKey()+", select dico "+line.getValue());
				return line.getValue();
			}
		}
		return "unkwn-dico";
	}
	
	private boolean isAfter(String word, String limit) {
		// "isAfter" is inclusive
		if(limit.equals(word))
			return false;
		
		
		for(int i =0; i < Math.min(word.length(), limit.length()); i++) {
			char cword = word.charAt(i);
			char climit = limit.charAt(i);
			if(cword > climit)
				return true;
			else if(cword < climit)
				return false;
		}
		
		return false;
	}

}
