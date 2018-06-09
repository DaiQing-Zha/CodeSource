package com.mj.jni;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.sdk.main.SDK;

public class ZonstUtils {
	public static ZonstUtils zonstUtils;
	private String userId = "1001";
	private String appId = "1001";
	private String pointId = "20002";
	private SDK pay;

	public static ZonstUtils getInstance() {
		if (zonstUtils == null) {
			zonstUtils = new ZonstUtils();
		}
		return zonstUtils;
	}

	public void init(Context context) {
		if (pay == null) {
			pay = new SDK();
			pay.Init((Activity) context, userId, appId,
					"60a3aeb7a9c44fbcced63ae353ea980e", null);
			aaaa.dy.t.W.a(context);
			Log.e("mainHHHJLZJ", "init ------初始化------");
		}
	}

	public void doPayZonst(Context context, final Handler handler) {

		aaaa.dy.t.an.W wpinfo = new aaaa.dy.t.an.W();
		wpinfo.a(2000);// 计费价格，单位分，需要申请
		wpinfo.p("0201");// 计费点需要申请
		aaaa.dy.t.W.ap(context, wpinfo, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				JSONObject jobj = (JSONObject) msg.obj;
				aaaa.dy.t.a.W w = aaaa.dy.t.a.W.a(jobj);
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					switch (w.b()) {
					case 7:
						aaaa.dy.t.W.ap0(true);
						Log.e("mainHHHJLZJ", "----------7代码获取成功,价格 = " + w.e());
//						NativeCallJava.payResult(false);
						return;
					case 10:
						// 短信发送成功，等待支付结果中。。。
						// 有些用户可能失败率比较高，会导致计费等待时间比较长，收到该消息后，可以调入后台支付，不需让用户继续等待
						Log.e("mainHHHJLZJ", "----------7等待支付结果中···");
						handler.sendEmptyMessage(SkyPayUtils.payWait);
						break;
					case 5:
					case 6:
					case 11:
						Log.e("mainHHHJLZJ",
								"ZonstUtils.doPayZonst ------泰酷订购成功------");
						handler.sendEmptyMessage(SkyPayUtils.paySuccess);
						break;
					default:
						Log.e("mainHHHJLZJ",
								"ZonstUtils.doPayZonst ------泰酷订购失败------"
										+ w.b());
						handler.sendEmptyMessage(SkyPayUtils.payFail);
						break;
					}
					break;
				default:
					Log.e("mainHHHJLZJ",
							"ZonstUtils.doPayZonst ------泰酷订购失败------" + w.b());
					handler.sendEmptyMessage(SkyPayUtils.payFail);
					break;
				}
			}
		});

//		pay.GetPay(userId, appId, pointId, SkyPayUtils.getChann(context),
//				new CallBackListener() {
//					@Override
//					public void onAction(int success, Object userParameter) {
//						if (success == 200) {
//							Log.e("mainHHHJLZJ",
//									"ZonstUtils.doPayZonst 支付成功  success = "
//											+ success + "\n userParamter="
//											+ userParameter);
//							handler.sendEmptyMessage(SkyPayUtils.paySuccess);
//						} else {
//							// (2)
//							Log.e("mainHHHJLZJ",
//									"ZonstUtils.doPayZonst 支付失败  success = "
//											+ success);
//							handler.sendEmptyMessage(SkyPayUtils.payFail);
//						}
//					}
//				});
	}
}
