package com.jyh.rest.listener;

import android.view.View;



/**
 *recyleview  item点击回调接口
 * 
 * @author wen_er
 * 
 */
public interface ItemClickListener {

	/**
	 * Item 普通点击
	 */
	public void onItemClick(View view, int postion);

	/**
	 * Item 长按
	 */

	public void onItemLongClick(View view, int postion);



}
