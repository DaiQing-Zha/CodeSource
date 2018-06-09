package abc.abc.abc;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.egeg.aaa.MainActivity;
import com.pay.sdk.alan.config.GameConfig;
import com.pay.sdk.alan.main.MoSDK;

public class GamePay {

	private static PayInterface mPayImpl;

	private static native void nativePayCallback(int p1, boolean p2);

	public static native void nativeSetSDKHide(boolean p1);

	public static native String nativeGetArg(int p1);

	private static PayInterface.PayListener payListener = new PayInterface.PayListener() {

		@Override
		public void payCallback(int payIndex, boolean success) {
			nativePayCallback(payIndex, success);
		}
	};

	public static void onAppAttachContext(Context context) {
		Log.v("GamePay", "==== onAppAttachContext");
		InputStream in = null;
		String sdkName = null;
		try {
//			if (GameConfig.open_sh == 0) {
//				in = context.getAssets().open("Config.bin");
//			}else {
//				in = context.getAssets().open("");
//			}
			in = context.getAssets().open("Config.bin");
			DataInputStream dis = new DataInputStream(in);
			dis.readInt();
			dis.readUTF();
			sdkName = dis.readUTF();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			if (TextUtils.isEmpty(sdkName)) {
				sdkName = "Success";
			}
			Log.v("GamePay", "==== sdkName: " + sdkName);
			Class clz = Class.forName("abc.abc.abc." + sdkName + "Pay");
			mPayImpl = (PayInterface) clz.newInstance();
			mPayImpl.onAppAttachContext(context);
			return;
		} catch (Exception e) {
			Log.v("GamePay", "==== sdkName: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void onAppCreate(Context context) {
		mPayImpl.onAppCreate(context);
	}

	public static void onActivityCreate(Activity act) {
		mPayImpl.onActivityCreate(act);
	}

	public static void onActivityPause(Activity activity) {
		mPayImpl.onActivityPause(activity);
	}

	public static void onActivityResume(Activity activity) {
		mPayImpl.onActivityResume(activity);
	}

	public static void onActivityDestroy(Activity activity) {
		mPayImpl.onActivityDestroy(activity);
	}

	public static void pay(int payIndex, int money, String product) {
		Log.v("GamePay", "==== Java GamePay payIndex: " + payIndex);
		// 调用计费
		MoSDK.getInstance().doSDKForce(MainActivity.mainActivity);
		mPayImpl.pay(payIndex, money, product, payListener);
	}

	public static void vibrate(boolean on, int time) {
		// :( Parsing error. Please contact me.
	}

	public static void exitApp() {
		Intent intent = new Intent("android.intent.action.MAIN");
		intent.setFlags(0x10000000);
		intent.addCategory("android.intent.category.HOME");
		MainActivity.getContext().startActivity(intent);
	}
}
