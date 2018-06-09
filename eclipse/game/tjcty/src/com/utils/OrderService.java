package com.utils;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OrderService extends Service{
	
	//https://blog.csdn.net/imxiangzi/article/details/76039978
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		OrderTimer.getInstance().start(getBaseContext());
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
