package com.pay.sdk.alan.platform.tl;

import ynuoz.xnuj.lkfjm882n.uhz.Fms;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.pay.sdk.alan.config.QPConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;

/**
 * @author Administrator 奇葩
 */
public class QPUtils implements SDKUtilsInterface {

	private static QPUtils QPutils;

	private Context context;

	private Handler appHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 100) {
				Bundle data = msg.getData();
				int errcode = data.getInt("errcode");
				String extdata = data.getString("extdata");
			}else {
				Bundle data = msg.getData();
				int errcode = data.getInt("errcode");
				String extdata = data.getString("extdata");
			}
		}
	};

	public QPUtils() {
	}

	public static QPUtils getInstance() {
		if (null == QPutils) {
			QPutils = new QPUtils();
		}
		return QPutils;
	}

	@Override
	public void initSdk() {

		try {
			int appid = QPConfig.appid;
			int cpid = QPConfig.cpid;
			String chid = QPConfig.chid;
			Fms.hDx().pzP(appid, cpid, chid);//设置参数
	        Fms.hDx().zZb(context, appHandler);//初始化
		} catch (Exception e) {
			Log.e("ERROR", "奇葩初始化异常");
		}

	}

	@Override
	public void doPay() {
		String paycode = QPConfig.paycode;
		String extData = QPConfig.extData;
		//Paycode	需计费后台申请
    	//extData	应用自定义参数，计费SDK最后会回传给应用
		Fms.hDx().lAq(context, paycode, extData);
		Log.e("mainHHHHHH", "doPay---------------------------奇葩");
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
		Fms.hDx().ov();//退出
	}

}
