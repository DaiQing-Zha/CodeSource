package com.pay.sdk.alan.platform.tl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.pay.sdk.alan.config.YFConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.y.f.jar.pay.BillingListener;
import com.y.f.jar.pay.YFPaySDK;

public class YFUtils implements SDKUtilsInterface {

	private YFPaySDK mJBilling;
	private static YFUtils YFutils;

	private Context context;

	public YFUtils() {
	}

	public static YFUtils getInstance() {
		if (null == YFutils) {
			YFutils = new YFUtils();
		}
		return YFutils;
	}
	
	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void initSdk() {
		/**
         * Public YfPaySDK(Activity gContext, BillingListener billingListener
         * , String appid, String distro, String fm)
         * gContext  一个Activity对象
         * billingListener   进行计费回调接口
         * appid    产品ID 就是分配的计费点excel表格的sheet2里的应用编号
         * distro  合作方信息 现在没用 填空字符串就可以 不要用中文
         * fm     渠道信息 区分不同包的 cp自己定义 不要用中文
         */
		String appid = YFConfig.appid;
		String distro = YFConfig.distro;
		String fm = YFConfig.fm;
        mJBilling = new YFPaySDK((Activity)context, new BillingListener() {
			public void onInitResult(int arg0) {
			}
			public void onBillingResult(int arg0, Bundle arg1) {
				switch(arg0){
				case 2000:
					Log.e("mainSMS", "----------8玉峰支付失败");
					break;
				default:
					Log.e("mainSMS", "----------8玉峰支付失败");
					break;
				}
			}
		}, appid, distro, fm);
		mJBilling.SetDebugMode(true);
	}

	@Override
	public void doPay() {
		String orderId = String.valueOf(System.currentTimeMillis());
		String payCode = YFConfig.payCode;
		String price = YFConfig.price;
    	mJBilling.pay(orderId,payCode, price);
	}

	@Override
	public void doPay(Context context) {
		
	}

	@Override
	public void destory() {
		
	}

}
