package com.pay.sdk.alan.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.pay.sdk.alan.config.GameConfig;
import com.pay.sdk.alan.config.SFConfig;
import com.pay.sdk.alan.config.TKConfig;
import com.pay.sdk.alan.config.WYConfig;
import com.pay.sdk.alan.config.YJConfig;
import com.pay.sdk.alan.config.ZDConfig;
import com.pay.sdk.alan.main.MoSDK;
import com.sdk.main.InitListener;
import com.sdk.main.Result;
import com.sdk.main.WYZFPay;
import com.shunpay208.sdk.ShunPay208;
import com.umeng.commonsdk.UMConfigure;

public class AlanApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
//		TestPay.getInstance().init();
//		CrashReport.initCrashReport(getApplicationContext(), "e0c5aa4d31", true); 
		/**
		* 初始化common库
		* 参数1:上下文，不能为空
		* 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
		* 参数3:Push推送业务的secret
		*/
//		if (ZhaUtil.isPrototypeAndHaveSim(getApplicationContext())) {
//			UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");	
//		}
		UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
		try {
			Log.e("mainMMM", "company sf");
			// 舜付
			if (GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_SF)) {
				int appId = SFConfig.appId;
				String channel = SFConfig.channel;
				String subChannel = SFConfig.subChannel;
				//ShunPay207.init(Context context, int appId, String channel, String subChannel);
				//appId，应用ID，由舜付分配
				//channel，渠道号，由CP自己定义
				//subChannel，子渠道号，由CP自己定义
				ShunPay208.init(this, appId, channel, subChannel);
			}
		} catch (Exception e) {
			Log.e("ERROR", "舜付初始化异常");
		}

		try {
			Log.e("mainMMM", "company tk");
			// 泰酷
			if (GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_TK)) {
				int appid = TKConfig.appid;
				String channelid = TKConfig.channelid;
				aaaa.dy.t.W.a("0");
				aaaa.dy.t.W.a(this, appid, channelid);
				
			}
		} catch (Exception e) {
			Log.e("ERROR", "泰酷初始化异常");
		}

		try {
			Log.e("mainMMM", "company wy");
			// 微云
			if (GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_WY)) {
				String appCode = WYConfig.appCode;
				String packCode = WYConfig.packCode;
				//WYZFPay.initSDK(Context, appCode,packCode, InitListener);
				//appCode: 游戏编号 微云支付分配
				//packCode:  渠道号(开发者自定义)
				//InitListener： 初始化监听器
		        WYZFPay.initSDK(this.getApplicationContext()
		        		, appCode, packCode, new InitListener() {
		            public void onInit(final Result initResult) {
		                if (Result.INITSUCCESS == initResult) {
		                	Log.e("mainSMS", "----------1微云初始化成功");
		                } else {
		                	Log.e("mainSMS", "----------1微云初始化失败");
		                }
		            }
		        });
			}
		} catch (Exception e) {
			Log.e("ERROR", "微云初始化异常");
		}
		
		try {
			Log.e("mainMMM", "company yj");
			if (GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_YJ)) {
//				N.setbd("n84d38", "n34b96.dat");
				//参数填写自家的参数,渠道号自定义（不能含有空格）
				String appidPre = YJConfig.appidPre;
				String appidSuf = YJConfig.appidSuf;
				String cpId = YJConfig.cpId;
				String channelId = YJConfig.channelId;
//				N.onCreate(this, appidPre, appidSuf, cpId, channelId);
			}
		} catch (Exception e) {
		}
		try {
			String channelId = ZDConfig.channelId;
			String productId = ZDConfig.productId;
//			SDKStart.getInstance().init(this, channelId, productId);
		} catch (Exception e) {
		}
	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		try {
			GameConfig.getInstatnce().init(this);
//			Onib.init(this);
		} catch (Exception e) {
			Log.e("ERROR", "获取配置文件异常");
		}
	}

}
