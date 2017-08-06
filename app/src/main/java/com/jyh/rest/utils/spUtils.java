package com.jyh.rest.utils;

import android.content.SharedPreferences;


/**SharedPreferences 的工具类*/
public class spUtils {
	private static SharedPreferences sp;

	private static void getInstance() {
		UIUtils.getContext();
		sp = UIUtils.getContext().getSharedPreferences("config", 0);
	}

	public static void put(String key, Object value) {
		if (sp == null) {
			getInstance();
		}
		if(value==null)return;
		if (value instanceof String) {
			sp.edit().putString(key, (String) value).commit();

		} else if (value instanceof Integer) {
			sp.edit().putInt(key, (Integer) value).commit();
		} else if (value instanceof Boolean) {
			sp.edit().putBoolean(key, (Boolean) value).commit();
		} else if (value instanceof Float) {
			sp.edit().putFloat(key, (Float) value).commit();
		} else if (value instanceof Long) {
			sp.edit().putLong(key, (Long) value).commit();
		} else {
			sp.edit().putString(key, value.toString()).commit();
		}
	}

	public static Object get(String key, Object defaultObject) {
		if (sp == null) {
			getInstance();
		}
		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}
		
		return null;
	}
	
	

}
