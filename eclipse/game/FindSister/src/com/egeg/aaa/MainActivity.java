package com.egeg.aaa;

import org.cocos2dx.lib.Cocos2dxActivity;

import com.pay.sdk.alan.config.GameConfig;
import com.pay.sdk.alan.main.MoSDK;
import com.pay.sdk.alan.platform.tl.PZUtils;
import com.pay.sdk.alan.tool.SystemTool;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;

import abc.abc.abc.GamePay;
import android.os.Bundle;

public class MainActivity extends Cocos2dxActivity {

	// 微云 捷梦 奇葩 平治 舜付 玉峰 泰酷 上岸
	public static MainActivity mainActivity;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity = this;
		GamePay.onActivityCreate(this);
		MoSDK.getInstance().initSDK(this);
//		MobclickAgent.onEvent(this, "pay00001");
	
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

	protected void onPause() {
		super.onPause();
		GamePay.onActivityPause(this);
		SystemTool.UmengAgentOnPause(this);
	}

	protected void onResume() {
		super.onResume();
		GamePay.onActivityResume(this);
		SystemTool.UmengAgentOnResume(this);
	}

	protected void onDestroy() {
		super.onDestroy();
		GamePay.onActivityDestroy(this);
		if (GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_PZ)) {
			PZUtils.getInstance().destory();
		}
	}

}
