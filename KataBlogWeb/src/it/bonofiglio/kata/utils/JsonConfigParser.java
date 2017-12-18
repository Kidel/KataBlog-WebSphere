package it.bonofiglio.kata.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonConfigParser {
	public Map<String, String> parse(String str) {
		try {
			String jsonString = str;
			return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>() {}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}