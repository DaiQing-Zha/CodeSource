package com.pay.sdk.alan.platform.tl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.pay.sdk.alan.config.SFConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.shunpay208.sdk.ShunPay208;

/**
 * @author Administrator 舜付
 */
public class SFUtils implements SDKUtilsInterface {

	private static SFUtils SFutils;

	private Context context;

	public SFUtils() {
	}

	public static SFUtils getInstance() {
		if (null == SFutils) {
			SFutils = new SFUtils();
		}
		return SFutils;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void initSdk() {

	}

	@Override
	public void doPay() {
		int itemId = SFConfig.itemId;
		int price = SFConfig.price;
    	//void ShunPay207.pay(Activity activity, int itemId, int price, PayListener listener);
    	//itemId， 计费点ID，由CP自己定义
    	//price, 计费点价格
		ShunPay208.pay((Activity)context, itemId, price, new ShunPay208.PayListener() {
			public void payResult(int result) {
				if (result == 1) {
					Log.e("mainSMS", "----------4舜付支付成功");
				}else {
					Log.e("mainSMS", "----------4舜付支付失败");
				}
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
