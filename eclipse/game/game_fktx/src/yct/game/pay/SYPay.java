package yct.game.pay;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.wo.main.WP_SDK;
import com.wo.plugin.WP_Event;

public class SYPay {
	
	public static void onPay(final Activity activity,int itemId) {
		try {
			String fee =itemId+"00";
			if (TextUtils.isEmpty(fee)) {
				Toast.makeText(activity, "金额不允许为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			WP_SDK.on_Recharge(activity, fee, "测试道具","测试道具",
					String.valueOf(System.currentTimeMillis()), 0, new WP_Event() {
						@Override
						public void on_Result(int code, String value) {
							Log.e("debug", code + ",value=" + value);
							if (code == 0) {// 充值成功
								Toast.makeText(activity, "充值成功!", Toast.LENGTH_LONG).show();
								GamePay.post(0);
								
							} else {// 充值失败
								Toast.makeText(activity, "充值失败!", Toast.LENGTH_LONG).show();
								
								GamePay.post(1);
							}
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
