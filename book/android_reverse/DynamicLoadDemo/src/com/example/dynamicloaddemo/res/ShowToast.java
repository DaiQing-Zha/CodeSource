package com.example.dynamicloaddemo.res;

import android.content.Context;
import android.widget.Toast;


public class ShowToast {
	
	private volatile static ShowToast mShowToast;
	
    private ShowToast(){} 
    /**
     * ��ȡ����ʵ��
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
     * ��ʼ��
     * @param context
     * @param msg
     */
	public void init(Context context, String msg) {
		Toast.makeText(context, "init==>" + msg, 1000).show();
	}
	/**
	 * ֧��
	 * @param context
	 * @param msg
	 */
	public void doPay(Context context, String msg) {
		Toast.makeText(context, "doPay==>" + msg, 1000).show();
	}
}
