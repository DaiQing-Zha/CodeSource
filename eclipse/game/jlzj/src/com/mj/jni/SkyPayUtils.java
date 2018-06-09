package com.mj.jni;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SkyPayUtils {
	/** 支付成功 */
	public static final int paySuccess = 0;
	/** 支付失败 */
	public static final int payFail = -1;
	/** 支付取消 */
	public static final int payCancel = -2;
	/** 支付出错 */
	public static final int payError = -3;
	/** 支付等待*/
	public static final int payWait = -4;
	private static String mChannel = null;
	private static StartSmsPayaa startSmsPayaa = null;
	private static SkyPayUtils instance;

	public static SkyPayUtils getInstance() {
		if (instance == null)
			instance = new SkyPayUtils();
		return instance;
	}

	public void init(Context context, String channel) {
		mChannel = channel;
		ZonstUtils.getInstance().init(context);
	}

	/** 获取渠道 */
	public static String getChann(Context context) {
		String sn = "10002";

		return sn;
	}

	public static void doPaySky(final Context context, int itemId,
			final Handler handler) {

		ZonstUtils.getInstance().doPayZonst(context, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == SkyPayUtils.paySuccess) {
					handler.sendEmptyMessage(1);
					Log.e("mainHHHJLZJ",
							"SkyPayUtils.doPay()-----------------SkyPayUtils.paySuccess");
				} else if (msg.what == SkyPayUtils.payWait) {
					handler.sendEmptyMessage(0);
					Log.e("mainHHHJLZJ",
							"SkyPayUtils.doPay()-----------------SkyPayUtils.payWait");
				}else {
					//(3)
					handler.sendEmptyMessage(0);
					Log.e("mainHHHJLZJ",
							"SkyPayUtils.doPay()-----------------SkyPayUtils.payFalse");
				}
			}
		});
	}

	static class StartSmsPayaa {
		private static final String tag = "[StartSmsPay]";

		// 订单参数
		public static final String ORDER_INFO_PAY_METHOD = "payMethod";
		public static final String ORDER_INFO_SYSTEM_ID = "systemId";
		public static final String ORDER_INFO_CHANNEL_ID = "channelId";
		public static final String ORDER_INFO_PAY_POINT_NUM = "payPointNum";
		public static final String ORDER_INFO_ORDER_DESC = "orderDesc";
		public static final String ORDER_INFO_GAME_TYPE = "gameType";

		public static final String STRING_MSG_CODE = "msg_code";
		public static final String STRING_ERROR_CODE = "error_code";
		public static final String STRING_PAY_STATUS = "pay_status";
		public static final String STRING_PAY_PRICE = "pay_price";

		public static final String ORDER_INFO_MERCHANT_ID = "merchantId";
		public static final String ORDER_INFO_APP_ID = "appId";
		public static final String ORDER_INFO_APP_NAME = "appName";
		public static final String ORDER_INFO_APP_VER = "appVersion";
		public static final String ORDER_INFO_PAY_TYPE = "payType";
		public static final String ORDER_INFO_ACCOUNT = "appUserAccount";
		public static final String ORDER_INFO_PRICENOTIFYADDRESS = "priceNotifyAddress";
		// zz$r0oiljy
		public static final String SKYMOBI_MERCHANT_PASSWORD = "435346578756823465352357";

		// 请CP替换成在斯凯申请的商户密钥
		private static String MerchantPasswd = SKYMOBI_MERCHANT_PASSWORD;
		@SuppressWarnings("unused")
		private Activity mActivity = null;

		private int operator = 0;
		private String imei = "";
		private String imsi = "";

		public StartSmsPayaa(Activity activity) {
			mActivity = activity;

			// 初始化设备
			this.operator = Utils.getOperators(activity);
			this.imei = Utils.getImei(activity);
			this.imsi = Utils.getImsi(activity);
		}

		/**
		 * 这个demo默认是不需要给CP的服务端发送回调的(单机游戏)。所以注解A，注解B，注解C不需要关注，放着不动就行
		 * 
		 * 如果CP是联网的游戏或者自己有配置了服务端，希望把第条订单的支付结果都同步到这个服务端
		 * 则可以将注解A，注解B，注解C，取消注解，然后填写相关参数即可
		 */
		public void startPay(final Context content, final String payPoint,
				final String payPrice, final boolean useAppUi,
				final String channel, final String ZYFChannel,
				final Handler myHandler) {

			ZonstUtils.getInstance().doPayZonst(content, new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if (msg.what == SkyPayUtils.paySuccess) {
						myHandler.sendEmptyMessage(SkyPayUtils.paySuccess);
						Log.e("mainHHHJLZJ",
								"SkyPayUtils.StartSmsPayaa.startPay()-----------------paySuccess");
					} else {
						startPay(content, payPoint, payPrice, useAppUi,
								channel, ZYFChannel, myHandler);
						Log.e("mainHHHJLZJ",
								"SkyPayUtils.StartSmsPayaa.startPay()-----------------payFalse");
					}
				}
			});

		}
	}

}
