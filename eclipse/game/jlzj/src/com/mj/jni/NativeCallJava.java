package com.mj.jni;

import java.util.HashMap;

import org.cocos2dx.lib.Cocos2dxActivity;

import com.jxzz.jlzj.SYPay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("UseSparseArrays")
public class NativeCallJava {
	private static final String TAG = NativeCallJava.class.getSimpleName();
	private static Cocos2dxActivity sActivity;
	private static AlertDialog mAlertDialog;
	private static Handler mHandler;

	private static int pageId;
	private static int orderId;
	private static int payIdInt;

	/** 价格 */
	public static final HashMap<Integer, Integer> priceInfo = new HashMap<Integer, Integer>();
	/** 名称 */
	public static final HashMap<Integer, String> nameInfo = new HashMap<Integer, String>();

	static {
		priceInfo.put(0, 8);
		priceInfo.put(1, 8);
		priceInfo.put(2, 8);
		priceInfo.put(3, 8);
		priceInfo.put(4, 10);
		priceInfo.put(5, 12);
		priceInfo.put(6, 8);
		priceInfo.put(7, 10);
		priceInfo.put(8, 12);
		priceInfo.put(9, 4);
		priceInfo.put(10, 10);
		priceInfo.put(11, 4);
		priceInfo.put(12, 8);
		priceInfo.put(13, 16);
		priceInfo.put(14, 10);
		priceInfo.put(15, 8);
		priceInfo.put(16, 0);

		nameInfo.put(0, "冰雪风暴x5");
		nameInfo.put(1, "破碎时空x5");
		nameInfo.put(2, "魔能光波x5");
		nameInfo.put(3, "冲击之刃x5");
		nameInfo.put(4, "天外飞仙");
		nameInfo.put(5, "撒旦之女");
		nameInfo.put(6, "宠物小炎魔");
		nameInfo.put(7, "宠物小师鹫");
		nameInfo.put(8, "宠物独角兽");
		nameInfo.put(9, "立即复活");
		nameInfo.put(10, "开启全部关卡");
		nameInfo.put(11, "一键升级");
		nameInfo.put(12, "一键满级");
		nameInfo.put(13, "终极大礼包");
		nameInfo.put(14, "结算翻牌");
		nameInfo.put(15, "精力礼包");
		nameInfo.put(16, "立即复活");
	};

	public static void init(Cocos2dxActivity paramCocos2dxActivity) {
		sActivity = paramCocos2dxActivity;
		mHandler = new Handler();
	}

	public static void doLog(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5) {
		Log.e(TAG, "[doLog] param1 = " + paramInt1 + ", param2 = " + paramInt2
				+ ", param3 = " + paramInt3 + ", param4 = " + paramInt4
				+ ", param5 = " + paramInt5);
	}

	public static void onExit(int paramInt1, int paramInt2, int paramInt3) {
		Log.d(TAG, "[onExit] param1 = " + paramInt1 + ", param2 = " + paramInt2
				+ ", param3 = " + paramInt3);
	}

	public static void postJavaResultOnGlThread(int paramInt1, int paramInt2,
			int paramInt3, int paramInt4) {
		Log.d(TAG, "[postJavaResultOnGlThread] param1 = " + paramInt1
				+ ", param2 = " + paramInt2 + ", param3 = " + paramInt3
				+ ", param4 = " + paramInt4);
	}

	public static void showPay(final int pageId, final int orderId,
			final int payIdInt, int arg1, int arg2) {
		NativeCallJava.pageId = pageId;
		NativeCallJava.orderId = orderId;
		NativeCallJava.payIdInt = payIdInt;
		mHandler.post(showPayPressDlog);
	}

	public static void toast(final String info, boolean paramBoolean) {
		sActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(sActivity, info, Toast.LENGTH_LONG).show();
			}
		});
	}

	private static Runnable showPayPressDlog = new Runnable() {
		public void run() {
			AlertDialog.Builder localBuilder = new AlertDialog.Builder(
					sActivity);
			localBuilder
					.setMessage("点击确定购买按钮表示您同意购买"
							+ nameInfo.get(payIdInt)
							+ "\n价格:20元，不含通讯费，将以短信形式提交订购，并由运营商从话费中扣除相应金额。客服热线:15168612206。");
			localBuilder.setPositiveButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							payResult(false);
							Log.e("mainHHHJLZJ",
									"NativeCallJava.取消----------------- payResult(false)");
						}
					});
			localBuilder.setNegativeButton("确定购买",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							if (payIdInt == 16) {
								payResult(true);
								Log.e("mainHHHJLZJ",
										"NativeCallJava.确定购买----------------- payResult(true) payIdInt = 16");
								return;
							}

							final int itemId = priceInfo.get(payIdInt);
							//(1)
							Log.e("mainHHHJLZJ",
									"NativeCallJava.确定购买----------------- payIdInt = " + payIdInt + " itemId = " + itemId);
							
							SkyPayUtils.doPaySky(sActivity, payIdInt,
									new Handler() {
										@Override
										public void handleMessage(Message msg) {
											super.handleMessage(msg);
											if (msg.what == 1) {
												payResult(true);
												Log.e("mainHHHJLZJ",
														"NativeCallJava.SkyPayUtils.doPay----------------- payResult(true)");
											} else {
												// payResult(false);
												// 首游微信
												SYPay.onPay(sActivity, pageId,
														orderId, payIdInt,
														itemId);
												//(4)
												Log.e("mainHHHJLZJ",
														"NativeCallJava.SkyPayUtils.doPay----------------- SYPay.onPay()");
											}
										}
									});

						}
					});
			localBuilder.create();
			mAlertDialog = localBuilder.show();
			mAlertDialog.setCancelable(false);
		}
	};

	public static void payResult(final boolean isSuccess) {
		sActivity.runOnGLThread(new Runnable() {
			@Override
			public void run() {
				NativeInterface.javaResult(pageId, orderId, payIdInt,
						isSuccess ? 0 : 1);
				Log.e("mainHHHJLZJ",
						"NativeInterface.javaResult----------------- result = " + isSuccess);
			}
		});
	}
}
