package com.myInterface;



import android.content.Context;

public interface SDKUtilsInterface {
	/** set context**/
	public void setContext(Context context);
	/**init SDK**/
	public void initSdk();
	/**pay1**/
	public void doPay();
	/**pay2**/
	public void doPay(Context context);
	/**注销**/
	public void destory();
}
