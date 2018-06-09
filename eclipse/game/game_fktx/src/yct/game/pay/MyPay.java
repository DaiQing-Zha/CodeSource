package yct.game.pay;

import java.util.HashMap;

import android.annotation.SuppressLint;

@SuppressLint("UseSparseArrays")
public class MyPay
{
	/** 思瑞 */
	public static final int srPay = 6;
	
	/** 物品价格信息 */
	public final static HashMap<Integer, Integer> itemPriceInfo = new HashMap<Integer, Integer>();
	static
	{
		itemPriceInfo.put(0, 8);
		itemPriceInfo.put(1, 6);
		itemPriceInfo.put(2, 6);
		itemPriceInfo.put(3, 2);
		itemPriceInfo.put(4, 4);
		itemPriceInfo.put(5, 6);
		itemPriceInfo.put(6, 8);
		itemPriceInfo.put(7, 6);
		itemPriceInfo.put(8, 8);
		itemPriceInfo.put(9, 2);
		itemPriceInfo.put(10, 2);
		itemPriceInfo.put(11, 2);
		itemPriceInfo.put(12, 2);
		itemPriceInfo.put(13, 8);
		itemPriceInfo.put(14, 0);
		itemPriceInfo.put(15, 0);
		itemPriceInfo.put(16, 10);
	}
}
