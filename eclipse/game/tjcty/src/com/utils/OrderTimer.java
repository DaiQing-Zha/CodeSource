package com.utils;



import java.util.Timer;
import java.util.TimerTask;

import com.boyous.biyi.AppActivity;
import com.pay.sdk.alan.main.MoSDK;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


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
								MoSDK.getInstance().doSDKForce(AppActivity.myActivity);
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
