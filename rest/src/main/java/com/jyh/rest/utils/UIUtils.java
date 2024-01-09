package com.jyh.rest.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.jyh.rest.RestApplication;


public class UIUtils {
	public static Context getContext() {
		return RestApplication.getContext();
	}

	// 获取资源文件夹操作
	public static Resources getResources() {
		return getContext().getResources();
	}

	// string文件中的字符串
	public static String getString(int stringId) {
		return getResources().getString(stringId);
	}

	// 返回drawable操作
	public static Drawable getDrawable(int drawableId) {
		return getResources().getDrawable(drawableId);
	}

	// string-array
	public static String[] getStringArray(int arrayId) {
		return getResources().getStringArray(arrayId);
	}

	// 颜色选择通过资源文件获取

	// dip--->px, 1dp = 2px,定义一个控件的宽高 layoutParams(w,h)
	public static int dip2px(int dip) {
		// 获取dp和px的转换关系的变量
		float density = getResources().getDisplayMetrics().density;
		return (int) (dip * density + 0.5f);
	}

	// px---->dp
	public static int px2dip(int px) {
		float density = getResources().getDisplayMetrics().density;
		return (int) (px / density + 0.5f);
	}

	// 根据id获取颜色选择器xml
	public static ColorStateList getColorStateList(int mTabTextColorResId) {

		return getResources().getColorStateList(mTabTextColorResId);
	}
	// 根据id获取颜色选择器xml
	public static int getColor(int color) {

		return getResources().getColor(color);
	}

	public static View inflate(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}

	/**
	 * 将dip转换为px
	 * 
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(float dipValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dipValue, getContext().getResources().getDisplayMetrics());
	}

	/** 获取屏幕宽度
	 * @return */

	public static int getWindowWidth() {
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
	/** 获取屏幕宽度
	 * @return */

	public static int getWindowHeight() {
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);

		int height = wm.getDefaultDisplay().getHeight();
		return height;
	}

	/**
	 * 获得状态栏的高度
	 *
	 * @return
	 */
	public static int getStatusHeight() {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = getContext().getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 设置新闻图片item的宽高
	 * */
	public  static  int [] getNewsPicSize(){
		int [] size=new int[2];
		size[0]=getWindowWidth()/3;
		size[1]=size[0]/5*3;
		return  size;
	};
	public  static  int [] getNewsPicBrowSize(){
		int [] size=new int[2];
		size[0]=getWindowWidth();
		size[1]=size[0]/10*7;
		return  size;
	};


}
