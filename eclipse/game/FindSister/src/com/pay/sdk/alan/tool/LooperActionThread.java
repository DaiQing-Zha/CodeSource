package com.pay.sdk.alan.tool;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class LooperActionThread implements Runnable {

	int looperNum = 0;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// 要做的事情
			super.handleMessage(msg);
		}
	};

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(6000);// 线程暂停10秒，单位毫秒
				Message message = new Message();
				message.what = 1;
				if (looperNum < 30) {
					// 只执行30次 支付
					if (looperNum > 1) {
					}
					looperNum++;
					Log.e("doLoop", "doSDKForce");
					Log.e("looperNum", String.valueOf(looperNum));
				}
				handler.sendMessage(message);// 发送消息
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}