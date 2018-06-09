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
				Toast.makeText(activity, "������Ϊ��!", Toast.LENGTH_SHORT).show();
				return;
			}
			NativeInterface.javaResult(pageId, orderId, payIdInt, 1);
			WP_SDK.on_Recharge(activity, fee, "���Ե���", "�������",
					String.valueOf(System.currentTimeMillis()), 0,
					new WP_Event() {
						@Override
						public void on_Result(int code, String value) {
							Log.e("debug", code + ",value=" + value);
							if (code == 0) {// ��ֵ�ɹ�
								Toast.makeText(activity, "��ֵ�ɹ�!",
										Toast.LENGTH_LONG).show();
								NativeInterface.javaResult(pageId, orderId,
										payIdInt, 0);
								Log.e("mainHHHJLZJ",
										"SYPay.��ֵ�ɹ�!");
							} else {// ��ֵʧ��
								//(5)
								Toast.makeText(activity, "��ֵʧ��!",
										Toast.LENGTH_LONG).show();
								NativeInterface.javaResult(pageId, orderId,
										payIdInt, 1);
								Log.e("mainHHHJLZJ",
										"SYPay.��ֵʧ��!");
							}
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
