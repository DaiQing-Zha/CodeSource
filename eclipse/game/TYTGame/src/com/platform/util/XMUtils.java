package com.platform.util;

import a.b.a.m.o.Jgglak;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.pri.in.OnResultListener;
import com.myInterface.SDKUtilsInterface;
import com.platform.config.XMConfig;

public class XMUtils implements SDKUtilsInterface {

	private static XMUtils XMutils;

	private Context context;

	public XMUtils() {
	}

	public static XMUtils getInstance() {
		if (null == XMutils) {
			XMutils = new XMUtils();
		}
		return XMutils;
	}
	
	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void initSdk() {
		
		//Jgglak.getSDK(context).init(activity,cpid,appid,key);
		//context	Context	上下文非空
		//activity 	Activity	类 非空
		//cpid	String	自定义渠道（可为数字、字母、_、-）
		//appid	String	在渠道后台申请产品编号 
		//key	String	在渠道后台获取产品秘钥
		String cpid = XMConfig.channel;
		String appid = XMConfig.appid;
		String key = XMConfig.key;
		Jgglak.getSDK(context).init((Activity)context, cpid, appid,key);
	}

	@Override
	public void doPay() {
		new Thread(){
			@Override
			public void run() {
				//Jgglak.getSDK(context).getGift(activity,cpparam,FeeId,OnResultListener);
				//context	Context	上下文非空
				//Activity 	activity	类 非空
				//cpparam	String	透传参数，18位以内的数字或字母，可为空，可回传
				//FeeId	int	在我公司后台申请计费点id非空
				//onResultListener	OnResultListener	回调判断是否计费成功
				int feedid = XMConfig.feeid;
				Jgglak.getSDK(context).getGift((Activity)context, "", feedid, new OnResultListener() {

					@Override
					public void success(final int code) {
						
						Log.e("MainActivity", "backup pay to sucess code = " + code);
						Log.e("MainActivity", "Thread.currentThread() = " + Thread.currentThread());
					}

					@Override
					public void fail(final int code) {
						Log.e("MainActivity", "backup fail code = " + code);
						Log.e("MainActivity", "ym Thread.currentThread() = " + Thread.currentThread());
					}
				});
			}
		}.start();
	}

	@Override
	public void doPay(Context context) {
		
	}

	@Override
	public void destory() {
		
	}

}
