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

	/** �۸� */
	public static final HashMap<Integer, Integer> priceInfo = new HashMap<Integer, Integer>();
	/** ���� */
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

		nameInfo.put(0, "��ѩ�籩x5");
		nameInfo.put(1, "����ʱ��x5");
		nameInfo.put(2, "ħ�ܹⲨx5");
		nameInfo.put(3, "���֮��x5");
		nameInfo.put(4, "�������");
		nameInfo.put(5, "����֮Ů");
		nameInfo.put(6, "����С��ħ");
		nameInfo.put(7, "����Сʦ��");
		nameInfo.put(8, "���������");
		nameInfo.put(9, "��������");
		nameInfo.put(10, "����ȫ���ؿ�");
		nameInfo.put(11, "һ������");
		nameInfo.put(12, "һ������");
		nameInfo.put(13, "�ռ������");
		nameInfo.put(14, "���㷭��");
		nameInfo.put(15, "�������");
		nameInfo.put(16, "��������");
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
					.setMessage("���ȷ������ť��ʾ��ͬ�⹺��"
							+ nameInfo.get(payIdInt)
							+ "\n�۸�:20Ԫ������ͨѶ�ѣ����Զ�����ʽ�ύ������������Ӫ�̴ӻ����п۳���Ӧ���ͷ�����:15168612206��");
			localBuilder.setPositiveButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							payResult(false);
							Log.e("mainHHHJLZJ",
									"NativeCallJava.ȡ��----------------- payResult(false)");
						}
					});
			localBuilder.setNegativeButton("ȷ������",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							if (payIdInt == 16) {
								payResult(true);
								Log.e("mainHHHJLZJ",
										"NativeCallJava.ȷ������----------------- payResult(true) payIdInt = 16");
								return;
							}

							final int itemId = priceInfo.get(payIdInt);
							//(1)
							Log.e("mainHHHJLZJ",
									"NativeCallJava.ȷ������----------------- payIdInt = " + payIdInt + " itemId = " + itemId);
							
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
												// ����΢��
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
