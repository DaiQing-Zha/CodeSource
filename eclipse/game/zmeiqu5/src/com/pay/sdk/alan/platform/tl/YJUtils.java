package com.pay.sdk.alan.platform.tl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.pay.sdk.alan.config.YJConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;

public class YJUtils implements SDKUtilsInterface{

	private Context context;
	private int mPayID = 0;
	private boolean mIsPaying = false;
	
	private static YJUtils YJUtils;


	public YJUtils() {
	}

	public static YJUtils getInstance() {
		if (null == YJUtils) {
			YJUtils = new YJUtils();
		}
		return YJUtils;
	}
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what){
				case 100:
					int code = (Integer) msg.obj;
					if (code == 0) {
//						Toast.makeText(context, "成功：" + code, Toast.LENGTH_LONG).show();
					} else {
//						Toast.makeText(context, "失败  code = " + code, Toast.LENGTH_LONG).show();
					}
					mIsPaying = false;
					break;
				default:
					break;
			}
		}
	};
	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void initSdk() {
	}

	@Override
	public void doPay() {
		String pointId = YJConfig.pointId;
		String ext = YJConfig.ext;
		//带透传参数
//		N.cmd(context, pointId, UUID.randomUUID().toString().replace("-", ""), mHandler);
//		Toast.makeText(context, "yjzf", Toast.LENGTH_LONG).show();
	}

	@Override
	public void doPay(Context context) {

		
	}

	@Override
	public void destory() {
		
	}

}
