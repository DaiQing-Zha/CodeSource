package com.jxzz.jlzj;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mj.jni.NativeInterface;
import com.wo.main.WP_SDK;
import com.wo.plugin.WP_Event;

public class SYPay {

	public static void onPay(final Activity activity, final int pageId,
			final int orderId, final int payIdInt, int itemId) {
		try {
			String fee = itemId + "00";
			if (TextUtils.isEmpty(fee)) {
				Toast.makeText(activity, "金额不允许为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			NativeInterface.javaResult(pageId, orderId, payIdInt, 1);
			WP_SDK.on_Recharge(activity, fee, "测试道具", "购买道具",
					String.valueOf(System.currentTimeMillis()), 0,
					new WP_Event() {
						@Override
						public void on_Result(int code, String value) {
							Log.e("debug", code + ",value=" + value);
							if (code == 0) {// 充值成功
								Toast.makeText(activity, "充值成功!",
										Toast.LENGTH_LONG).show();
								NativeInterface.javaResult(pageId, orderId,
										payIdInt, 0);
								Log.e("mainHHHJLZJ",
										"SYPay.充值成功!");
							} else {// 充值失败
								//(5)
								Toast.makeText(activity, "充值失败!",
										Toast.LENGTH_LONG).show();
								NativeInterface.javaResult(pageId, orderId,
										payIdInt, 1);
								Log.e("mainHHHJLZJ",
										"SYPay.充值失败!");
							}
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
