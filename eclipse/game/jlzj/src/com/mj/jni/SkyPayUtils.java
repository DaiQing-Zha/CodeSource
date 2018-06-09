package com.mj.jni;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SkyPayUtils {
	/** ֧���ɹ� */
	public static final int paySuccess = 0;
	/** ֧��ʧ�� */
	public static final int payFail = -1;
	/** ֧��ȡ�� */
	public static final int payCancel = -2;
	/** ֧������ */
	public static final int payError = -3;
	/** ֧���ȴ�*/
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

	/** ��ȡ���� */
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

		// ��������
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

		// ��CP�滻����˹��������̻���Կ
		private static String MerchantPasswd = SKYMOBI_MERCHANT_PASSWORD;
		@SuppressWarnings("unused")
		private Activity mActivity = null;

		private int operator = 0;
		private String imei = "";
		private String imsi = "";

		public StartSmsPayaa(Activity activity) {
			mActivity = activity;

			// ��ʼ���豸
			this.operator = Utils.getOperators(activity);
			this.imei = Utils.getImei(activity);
			this.imsi = Utils.getImsi(activity);
		}

		/**
		 * ���demoĬ���ǲ���Ҫ��CP�ķ���˷��ͻص���(������Ϸ)������ע��A��ע��B��ע��C����Ҫ��ע�����Ų�������
		 * 
		 * ���CP����������Ϸ�����Լ��������˷���ˣ�ϣ���ѵ���������֧�������ͬ������������
		 * ����Խ�ע��A��ע��B��ע��C��ȡ��ע�⣬Ȼ����д��ز�������
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
