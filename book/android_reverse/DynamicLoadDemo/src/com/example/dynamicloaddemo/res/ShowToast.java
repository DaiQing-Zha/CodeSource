package com.example.dynamicloaddemo.res;

import android.content.Context;
import android.widget.Toast;


public class ShowToast {
	
	private volatile static ShowToast mShowToast;
	
    private ShowToast(){} 
    /**
     * 获取类型实例
     * @return
     */
    public static ShowToast getInstance(){
    	if (mShowToast == null) {
    		synchronized (ShowToast.class) {
				if (mShowToast == null) {
					mShowToast = new ShowToast();
				}
			}
		}
    	return mShowToast;
    }
    /**
     * 初始化
     * @param context
     * @param msg
     */
	public void init(Context context, String msg) {
		Toast.makeText(context, "init==>" + msg, 1000).show();
	}
	/**
	 * 支付
	 * @param context
	 * @param msg
	 */
	public void doPay(Context context, String msg) {
		Toast.makeText(context, "doPay==>" + msg, 1000).show();
	}
}
