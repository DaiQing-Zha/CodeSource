package com.pay.sdk.alan.platform.tl;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jm.jiesdk.JiePayResultListener;
import com.jm.jiesdk.constant.JiePayResult;
import com.pay.sdk.alan.config.JMConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.wchen.jiezf.JiePaySDK;

/**
 * @author Administrator 捷梦 654321
 */
public class JMUtils implements SDKUtilsInterface {

	private static JMUtils JMutils;

	private Context context;

	public JMUtils() {
	}

	public static JMUtils getInstance() {
		if (null == JMutils) {
			JMutils = new JMUtils();
		}
		return JMutils;
	}

	@Override
	public void initSdk() {
		try {
			//JiePaySDK.getInstance().init(Context context, String JIEPAY_APPID, String JIEPAY_CHANNEL);
			//JIEPAY_APPID	游戏ID	由捷支付分配
			//JIEPAY_CHANNEL	渠道号	由cp自定义（最大长度20，只能包含数字、字母、下划线，不能包含其他特殊字符）
//			JiePaySDK.getInstance().init(context);
			String appId = JMConfig.appid;
			String channel = JMConfig.channelid;
			JiePaySDK.getInstance().init(context, appId, channel);
		} catch (Exception e) {
			Log.e("ERROR", "捷梦初始化异常");
		}

	}

	@Override
	public void doPay() {
    	//JiePaySDK.getInstance().jpay (Activity context,int feeCode, String extData,JiePayResultListener resultListener);
    	//feeCode	计费点ID	由捷支付分配
    	//resultListener	支付结果回调接口
		int feeCode = JMConfig.feeCode;
		JiePaySDK.getInstance().jpay ((Activity)context,feeCode, "",new JiePayResultListener() {
			@Override
			public void onResult(JiePayResult payResult, int feeCode) {
				switch(payResult){
				case SUCCESS:
					Log.e("mainSMS", "----------2捷梦支付成功");
					break;
				default:
					Log.e("mainSMS", "----------2捷梦支付失败");
				}	
			}
		});
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
