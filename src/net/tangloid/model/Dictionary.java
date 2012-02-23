package net.tangloid.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import net.tangloid.util.ResLoader;

public class Dictionary {
	
	private List<String> words;
	private Context context;
	
	public Dictionary(Context context) {
		
		this.context = context;
		words = new ArrayList<String>();
		words.add("ABC");
		words.add("AB");

		
	}
	
	public boolean isValidWord(String testedWord) {
		return ResLoader.isWordExists(context, "fr", testedWord);
	}

}
