package com.fktxpoiuyt.fktx;

import org.cocos2dx.lib.Cocos2dxActivity;

import yct.game.pay.GamePay;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.fktxpoiuyt.config.SystemTool;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;

public class MainActivity extends Cocos2dxActivity {	
	public static MainActivity instance;
	static {
		System.loadLibrary("cocos2dcpp");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		GamePay.setActivity(this);
		GamePay.rankInstance = new yct.game.pay.c();
	    GamePay.rankInstance.a(this);
	    PayUtil.init(this);//初始化支付
	    
	    /**
		* 设置组件化的Log开关
		* 参数: boolean 默认为false，如需查看LOG设置为true
		*/
		UMConfigure.setLogEnabled(true);
		/**
		* 设置日志加密
		* 参数：boolean 默认为false（不加密）
		*/
		UMConfigure.setEncryptEnabled(true);
		MobclickAgent.setScenarioType(this, EScenarioType.E_UM_NORMAL);
	}
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}
	public void onPause() {
		super.onPause();
		SystemTool.UmengAgentOnPause(this);
	}
	public void onResume() {
		super.onResume();
		SystemTool.UmengAgentOnResume(this);
	}
	private static ProgressDialog setInfoDialog = null;
	public static void showDialog() {
		instance.runOnUiThread(new Runnable() {// 必须要在UI主线程中 更新画面显示
			@Override
			public void run() {
				if (setInfoDialog == null) {
					setInfoDialog = new ProgressDialog(instance, ProgressDialog.STYLE_HORIZONTAL);
					setInfoDialog.setMessage("请稍后...");
					setInfoDialog.setCancelable(false);
					setInfoDialog.show();
				}
			}
		});
	}
	public static void closeDialog() {
		instance.runOnUiThread(new Runnable() {// 必须要在UI主线程中 更新画面显示
			@Override
			public void run() {
				if (setInfoDialog != null) {
					setInfoDialog.dismiss();
					setInfoDialog = null;
				}
			}
		});
	}
}
