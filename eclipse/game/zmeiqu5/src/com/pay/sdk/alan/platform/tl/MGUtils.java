package com.pay.sdk.alan.platform.tl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.kIXwj.uFzUYListener;
import com.kIXwj.zLpRF;
import com.pay.sdk.alan.config.MGConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;

public class MGUtils implements SDKUtilsInterface{

	private static MGUtils MGUtils;

	private Context context;

	public MGUtils() {
	}

	public static MGUtils getInstance() {
		if (null == MGUtils) {
			MGUtils = new MGUtils();
		}
		return MGUtils;
	}
	
	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void initSdk() {
		try {

			String maiMsa = MGConfig.maiMsa;
			String channel = MGConfig.maiChannelId;
			zLpRF.getInstance().init((Activity)context, maiMsa, channel);
		} catch (Exception e) {
			Log.e("ERROR", "麦广初始化异常");
		}
	}

	@Override
	public void doPay() {
		Log.e("mainHHH", "mg-----------------------------");
		String gid = MGConfig.gid;
		String extra = MGConfig.ext;
		zLpRF.getInstance().star((Activity) context, gid, "mg" + System.currentTimeMillis(), extra, new uFzUYListener() {
			
			@Override
			public void callback(String arg0, int arg1, int arg2, String arg3) {
				Log.e("mainHHH", "arg1 = " + arg1 + " arg2 = " + arg2);
			}
		});
	}

	@Override
	public void doPay(Context context) {
		
	}

	@Override
	public void destory() {
		
	}

}
