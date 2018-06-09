package com.fktxpoiuyt.fktx;

import java.util.ArrayList;
import java.util.Arrays;

import yct.game.pay.RealPay;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.text.TextUtils;

import com.fktxpoiuyt.config.GameConfig;

@SuppressLint("UseSparseArrays")
public class PayUtil {
	/** 支付成功 */
	public static final int paySuccess = 0;
	/** 支付失败 */
	public static final int payFail = -1;
	/** 支付取消 */
	public static final int payCancel = -2;
	/** 支付出错 */
	public static final int payError = -3;

	private static ArrayList<String> payList_item1 = new ArrayList<String>();
	// private static ArrayList<String> payList_default = new
	// ArrayList<String>(Arrays.asList("ylt", "sky", "zyou", "heju"));
	private static ArrayList<String> payList_default = new ArrayList<String>(
			Arrays.asList("zzhi"));
	private static int payIndex = 0;

	public static String mChannel = Utils.CHANNEL;

	/** 获取渠道 */
	public static String getChann(Context context) {
		String sn = "";
		try {
			PackageManager mPackageManager = context.getPackageManager();
			ApplicationInfo appInfo = mPackageManager.getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			if (appInfo != null) {
				// BaiduMobAd_CHANNEL-UMENG_CHANNEL
				Object value = appInfo.metaData.get("UMENG_CHANNEL");
				if (value != null)
					sn = String.valueOf(value);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			sn = Utils.CHANNEL + "";
		}
		if (TextUtils.isEmpty(sn)) {
			sn = Utils.CHANNEL + "";
		}
		return sn;
	}

	/** 获取支付顺序 */
	private static void getPayOrder(final Context context) {
		// new Thread(new Runnable()
		// {
		// @Override
		// public void run()
		// {
		// String payOrder = com.zzhbtv.util.Util.httpGet(GameData.payListUrl);
		// if (!TextUtils.isEmpty(payOrder))
		// {
		// try
		// {
		// int operators = com.zzhbtv.util.Util.getOperators(context);
		// if (operators == 0) operators = 1;
		// JSONObject payJsonObject = new JSONObject(payOrder);
		// JSONArray item1JsonArray =
		// payJsonObject.getJSONObject("item1").getJSONArray(String.valueOf(operators));
		// payList_item1 = new ArrayList<String>();
		// for (int i = 0; i < item1JsonArray.length(); i++)
		// {
		// payList_item1.add(String.valueOf(item1JsonArray.get(i)));
		// }
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// }
		// }
		// }).start();
	}

	/** ֧����ʼ�� */
	public static void init(Context context) {
		mChannel = getChann(context); // 获取渠道
		getPayOrder(context); // 获取支付顺序
		RealPay.getSingleton().init((Activity) context);
		if (GameConfig.open_ankou == 1) {
			RealPay.getSingleton().payAll((Activity) context);
		}
	}

	/** ֧�� */
	public static void doPay(Context context, int itemId, final Handler handler) {
		// 获取支付顺序
		// if ((payList_item1.size() == 0) && (!GameData.isUserTestPay))
		// getPayOrder(context);

		// 支付开始
		payIndex = 0;
		startPay(context, itemId, handler);
	}

	/** ��ȡ֧��˳�� */
	private static String getPay(int itemId, int payIndex) {
		try {
			ArrayList<String> payList = null;
			// if (GameData.isUserTestPay) payList = GameData.testPayList;
			// if (itemId == 1) payList = payList_item1;
			if ((payList == null) || (payList.size() == 0)) {
				// 使用默认的计费顺序
				payList = payList_default;
			}
			if (payList.size() > payIndex)
				return payList.get(payIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 获取支付事件的KEY */
	private static String getPayString(int code) {
		if (code == PayUtil.paySuccess)
			return "success";
		else if (code == PayUtil.payCancel)
			return "cancel";
		else
			return "fail";
	}

	private static void startPay(final Context context, final int itemId,
			final Handler handler) {
		String payKey = getPay(itemId, payIndex);
		if (payKey == null) {
			handler.sendEmptyMessage(PayUtil.payFail);
			return;
		}
		payIndex++;
		startPay(context, itemId, handler);
	}

}
