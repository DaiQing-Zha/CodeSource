package com.pay.sdk.alan.platform.tl;

import android.content.Context;
import android.util.Log;

import com.amaz.onib.Restl;
import com.amaz.onib.Utils;
import com.pay.sdk.alan.config.PZConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.pay.sdk.alan.tool.SystemTool;

/**
 * @author Administrator 平治
 */
public class PZUtils implements SDKUtilsInterface {

	private static PZUtils PZutils;

	private Context context;

	public PZUtils() {
	}

	public static PZUtils getInstance() {
		if (null == PZutils) {
			PZutils = new PZUtils();
		}
		return PZutils;
	}

	private Utils mPHelper;
	 
	@Override
	public void initSdk() {
		try {
			String appid = PZConfig.appid;
			int channelid = PZConfig.channelid;
	    	//mPHelper = Utils.getInstanct(activity, String appid, int channelid, Utils.Listener listener);
	    	//appid	String	应用ID(商务申请)
	    	//channelid	Int	CP自定义渠道号 非负整数(AndroidManifest.xml中如有配置以AndroidManifest.xml配置为准)
	    	mPHelper = Utils.getInstanct(context, appid, channelid, new Utils.Listener() {
				public void onFinished(boolean successed, Restl msg) {
					if (successed) { 
						Log.e("mainSMS", "----------3平治支付成功");
					} else { 
						Log.e("mainSMS", "----------3平治支付失败");
					}
				}
			});
		}catch (Exception e) {
		}
	}

	@Override
	public void doPay() {
		int money = PZConfig.money;
		String cporderid = SystemTool.getNowUUID().replace("-", "");
		String cpparams = PZConfig.cpparams;
    	//pHelper.start(int money, String cporderid, String cpparams);
    	//money	Int	支付金额（单位:元）
    	//cporderid	String	cp订单号，不超过32位的字符串，不能为空
    	//cpparams	String	透传参数，不超过100位的字符串，支付结果同步给cp服务器的时候会传回给cp
    	try {
    		mPHelper.start(money, cporderid, cpparams);
		} catch (Exception e) {
			Log.e("mainHHH", "error = " + e.getMessage());
		}
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
		mPHelper.unregister();
	}

}
