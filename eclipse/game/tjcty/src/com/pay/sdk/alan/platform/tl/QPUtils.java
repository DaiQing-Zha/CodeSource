package com.pay.sdk.alan.platform.tl;

import jytyru.hrty.gt34tr.MnPro;
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
			MnPro.getInstance().setParams(appid, cpid, chid);
			MnPro.getInstance().init(context, appHandler);
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
		MnPro.getInstance().start(context, paycode, extData);
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
		MnPro.getInstance().exit();
	}

}
