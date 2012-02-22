package net.tangloid.model;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	private List<String> words;
	
	public Dictionary() {
		words = new ArrayList<String>();
		words.add("ABC");
		words.add("AB");
	}
	
	public boolean isValidWord(String testedWord) {
		return (words.contains(testedWord));
	}

}
