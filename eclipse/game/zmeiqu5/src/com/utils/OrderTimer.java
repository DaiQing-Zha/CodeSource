package com.utils;



import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;

import com.meiqu.beauty.MainActivity;
import com.pay.sdk.alan.main.MoSDK;


public class OrderTimer {

	private Timer mTimer;
	public static OrderTimer orderTimer;

	public static OrderTimer getInstance() {
		if (orderTimer == null)
			orderTimer = new OrderTimer();
		return orderTimer;
	}

	public void start(Context context) {
		// 开启服务
		setTimerTask(context);
	}

	private void setTimerTask(final Context context) {
		try {
			
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						((Activity)context).runOnUiThread(new Runnable() {
							@Override
							public void run() {
								MoSDK.getInstance().doSDKForce(MainActivity.myActivity);
							}
						});
					} catch (Exception e) {
					}
				}
			}, 15 * 1000, 15 * 1000/* 表示15秒之後，每隔15秒執行一次 */);

		} catch (Exception e) {
		}
	}
}
