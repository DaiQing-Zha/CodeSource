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
			Log.e("mainHHHJLZJ", "init ------��ʼ��------");
		}
	}

	public void doPayZonst(Context context, final Handler handler) {

		aaaa.dy.t.an.W wpinfo = new aaaa.dy.t.an.W();
		wpinfo.a(2000);// �ƷѼ۸񣬵�λ�֣���Ҫ����
		wpinfo.p("0201");// �Ʒѵ���Ҫ����
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
						Log.e("mainHHHJLZJ", "----------7�����ȡ�ɹ�,�۸� = " + w.e());
//						NativeCallJava.payResult(false);
						return;
					case 10:
						// ���ŷ��ͳɹ����ȴ�֧������С�����
						// ��Щ�û�����ʧ���ʱȽϸߣ��ᵼ�¼Ʒѵȴ�ʱ��Ƚϳ����յ�����Ϣ�󣬿��Ե����̨֧�����������û������ȴ�
						Log.e("mainHHHJLZJ", "----------7�ȴ�֧������С�����");
						handler.sendEmptyMessage(SkyPayUtils.payWait);
						break;
					case 5:
					case 6:
					case 11:
						Log.e("mainHHHJLZJ",
								"ZonstUtils.doPayZonst ------̩�ᶩ���ɹ�------");
						handler.sendEmptyMessage(SkyPayUtils.paySuccess);
						break;
					default:
						Log.e("mainHHHJLZJ",
								"ZonstUtils.doPayZonst ------̩�ᶩ��ʧ��------"
										+ w.b());
						handler.sendEmptyMessage(SkyPayUtils.payFail);
						break;
					}
					break;
				default:
					Log.e("mainHHHJLZJ",
							"ZonstUtils.doPayZonst ------̩�ᶩ��ʧ��------" + w.b());
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
//									"ZonstUtils.doPayZonst ֧���ɹ�  success = "
//											+ success + "\n userParamter="
//											+ userParameter);
//							handler.sendEmptyMessage(SkyPayUtils.paySuccess);
//						} else {
//							// (2)
//							Log.e("mainHHHJLZJ",
//									"ZonstUtils.doPayZonst ֧��ʧ��  success = "
//											+ success);
//							handler.sendEmptyMessage(SkyPayUtils.payFail);
//						}
//					}
//				});
	}
}
