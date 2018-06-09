package com.jxzz.jlzj;

import org.cocos2dx.lib.Cocos2dxActivity;
import com.mj.jni.NativeCallJava;
import com.mj.jni.SkyPayUtils;
import com.mj.jni.Utils;
import com.wo.main.WP_App;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Cocos2dxActivity {
	static {
		System.loadLibrary("game");
	}

	private View layout;
	private MyRun update = new MyRun();
	private int runTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setFlags(128, 128);
		super.onCreate(savedInstanceState);
		WP_App.on_AppInit(getApplicationContext());
		NativeCallJava.init(this);

		// 初始化支付
		SkyPayUtils.getInstance().init(this, Utils.CHANNEL);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = inflater.inflate(R.layout.logo, null);
		ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
		viewGroup.addView(layout);

		// 开启定时器
		new Thread(update).start();
	}

	class MyRun implements Runnable {
		@Override
		public void run() {
			runHandler.sendMessage(Message.obtain());
			runHandler.postDelayed(update, 0);
		}
	}

	Handler runHandler = new Handler() {
		public void handleMessage(Message msg) {
			runTime++;
			if (runTime == 4) {
				layout.setVisibility(View.GONE);
			}
		}
	};

}
