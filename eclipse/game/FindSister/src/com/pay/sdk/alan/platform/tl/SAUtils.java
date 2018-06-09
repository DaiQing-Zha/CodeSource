package com.pay.sdk.alan.platform.tl;

import android.content.Context;
import android.util.Log;

import cn.cuter.main.ResultCallback;
import cn.cuter.main.Uncm;

import com.pay.sdk.alan.config.SAConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;

/**
 * @author Administrator 上岸
 */
public class SAUtils implements SDKUtilsInterface {

	private static SAUtils SAutils;

	private Context context;

	public SAUtils() {
	}

	public static SAUtils getInstance() {
		if (null == SAutils) {
			SAutils = new SAUtils();
		}
		return SAutils;
	}

	@Override
	public void initSdk() {
		String channel = SAConfig.channel;
		Uncm.ins.init(context ,channel ,new ResultCallback() {
			@Override
			public void onSuccess() {
				Log.e("上岸", "支付成功");
			}

			@Override
			public void onFailed(int code) {
				Log.e("上岸", "支付失败");
				Log.e("code：", String.valueOf(code));
			}
		});
	}

	@Override
	public void doPay() {
		
		Uncm.ins.pay();
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void doPay(Context context) {

	}

	@Override
	public void destory() {

	}
}
