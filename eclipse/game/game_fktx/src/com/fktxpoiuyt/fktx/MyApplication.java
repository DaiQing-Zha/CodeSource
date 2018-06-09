package com.fktxpoiuyt.fktx;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.cuter.main.ResultCallback;
import cn.cuter.main.Uncm;

import com.amaz.on.Onib;
import com.fktxpoiuyt.config.GameConfig;
import com.fktxpoiuyt.config.JMConfig;
import com.fktxpoiuyt.config.QPConfig;
import com.fktxpoiuyt.config.SAConfig;
import com.fktxpoiuyt.config.SFConfig;
import com.fktxpoiuyt.config.TKConfig;
import com.fktxpoiuyt.config.WYConfig;
import com.sdk.main.InitListener;
import com.sdk.main.Result;
import com.sdk.main.WYZFPay;
import com.shunpay208.sdk.ShunPay208;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.utils.ZhaUtil;
import com.wchen.jiezf.JiePaySDK;

public class MyApplication extends Application {
	
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
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		try {
			GameConfig.getInstatnce().init(this);
			Onib.init(this);
		} catch (Exception e) {
			Log.e("ERROR", "获取配置文件异常");
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		CrashReport.initCrashReport(getApplicationContext(), "e0c5aa4d31", true); 
		/**
		* 初始化common库
		* 参数1:上下文，不能为空
		* 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
		* 参数3:Push推送业务的secret
		*/
		if (ZhaUtil.isPrototypeAndHaveSim(getApplicationContext())) {
			UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
		}
		
		String channelSA = SAConfig.channel;
		Uncm.ins.init(this.getApplicationContext() ,channelSA ,new ResultCallback() {
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
		try {
			// 舜付
			int appId = SFConfig.appId;
			String channel = SFConfig.channel;
			String subChannel = SFConfig.subChannel;
			//ShunPay207.init(Context context, int appId, String channel, String subChannel);
			//appId，应用ID，由舜付分配
			//channel，渠道号，由CP自己定义
			//subChannel，子渠道号，由CP自己定义
			ShunPay208.init(this, appId, channel, subChannel);
		} catch (Exception e) {
			Log.e("ERROR", "舜付初始化异常");
		}

		try {
			// 泰酷
			int appid = TKConfig.appid;
			String channelid = TKConfig.channelid;
			aaaa.dy.t.W.a("0");
			aaaa.dy.t.W.a(this, appid, channelid);
		} catch (Exception e) {
			Log.e("ERROR", "̩���ʼ���쳣");
		}

		try {
			// 微云
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
	                	Log.e("mainSMS", "----------1΢�Ƴ�ʼ���ɹ�");
	                } else {
	                	Log.e("mainSMS", "----------1΢�Ƴ�ʼ��ʧ��");
	                }
	            }
	        });
		} catch (Exception e) {
			Log.e("ERROR", "΢�Ƴ�ʼ���쳣");
		}
		
		//JiePaySDK.getInstance().init(Context context, String JIEPAY_APPID, String JIEPAY_CHANNEL);
				//JIEPAY_APPID	游戏ID	由捷支付分配
				//JIEPAY_CHANNEL	渠道号	由cp自定义（最大长度20，只能包含数字、字母、下划线，不能包含其他特殊字符）
//				JiePaySDK.getInstance().init(context);
		String appId = JMConfig.appid;
		String channel = JMConfig.channelid;
		JiePaySDK.getInstance().init(this.getApplicationContext(), appId, channel);
		
		try {
			int appid = QPConfig.appid;
			int cpid = QPConfig.cpid;
			String chid = QPConfig.chid;
//	        DdP.getInstance().setCs(appid, cpid, chid);
//	        DdP.getInstance().csh(this.getApplicationContext(), appHandler);
		} catch (Exception e) {
			Log.e("ERROR", "奇葩初始化异常");
		}

	}

}
