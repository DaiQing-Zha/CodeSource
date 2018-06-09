package yct.game.pay;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.fktxpoiuyt.config.GameConfig;
import com.fktxpoiuyt.fktx.MainActivity;
import com.fktxpoiuyt.fktx.Utils;
import com.umeng.analytics.MobclickAgent;

public class GamePay {
	private static AlertDialog mAlertDialog = null;
	public static final int PAY_EXCEPTION = 2;
	public static final int PAY_FAILED = 1;
	public static final int PAY_PASS = 0;
	public static final int PAY_SUCCESS = 0;
	public static b rankInstance;

	private static Activity act;
	private static MyRun update = new MyRun();
	private static boolean isSendSyncTime = false;
	private static boolean isGetRank = false;

	private static PayDialog payDialog;
	private static int PayID = 0;

	public static void setActivity(Activity a) {
		act = a;
		// 初始化数据模块
		// Data.getInstance().init(act);
		// 初始化支付Dialog
		payDialog = new PayDialog(act, 1);
		payDialog.dismiss();
		payDialog.setConfirmOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (GameConfig.open_et == 1) {
					MainActivity.showDialog();
					mHandler.post(showPayPressDlog);
					payDialog.dismiss();
					
				}else {
					RealPay.getSingleton().payAll(act);
					payDialog.dismiss();
					MainActivity.closeDialog();
					GamePay.post(0); 
				}

				// 发送友盟统计（计费点点击）
				//SendPayPointClickEvents(act, PayID);

				act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// SYPay.onPay(act, 1);
						// // 调用支付
						/*
						 * PayUtil.doPay(act, PayID, new Handler() {
						 * 
						 * @Override public void handleMessage(Message msg) {
						 * super.handleMessage(msg); MainActivity.closeDialog();
						 * if (msg.what == PayUtil.paySuccess) {
						 * GamePay.post(0); } else { SYPay.onPay(act,PayID);
						 * //GamePay.post(1); } } });
						 */

					}
				});
				// System.out.println("=====调用支付");
				//
				// System.out.println("=====PayID:"+PayID);
				// new MyPay(PayID, act, new MyPayCallback() {
				//
				// @Override
				// public void payComplete(boolean isSuccess) {
				// payDialog.dismiss();
				// if (isSuccess)
				// post(0);
				// else
				// post(1);
				// }
				// });
			}
		});
		payDialog.setCancelOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				payDialog.dismiss();
				post(1);
			}
		});
		// 开启定时器
		new Thread(update).start();
	}

	private static class MyRun implements Runnable {
		@Override
		public void run() {
			mHandler.sendMessage(Message.obtain());
			mHandler.postDelayed(update, 100);
		}
	}

	static Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (isSendSyncTime) {
				isSendSyncTime = false;
				endSyncTime(true, 1);
			}
			if (isGetRank) {
				isGetRank = false;
				endGetRank(false, new int[] { 0, 0, 0, 0, 0, 0, 0, 0 });
			}
		}
	};

	public static int BeginGetRank() {
		isGetRank = true;
		return 1;
	}

	public static int BeginSyncTime() {
		isSendSyncTime = true;
		return rankInstance.a();
	}

	public static void Event(int paramInt) {
		Log.d("GamePay", "[Event]paramInt = " + paramInt);
	}

	public static int GetPolicy() {
		return 0;
	}

	public static void Pay(int paramInt) {

		Log.d("GamePay", "[Pay]paramInt = " + paramInt);
		PayID = paramInt;
		act.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				payDialog.show();
				payDialog.setPayID(PayID);
			}
		});
	}

	public static void PostDistance(int paramInt) {
		rankInstance.a(paramInt);
		Log.d("GamePay", "[PostDistance]paramInt = " + paramInt);
	}

	public static native void endGetRank(boolean paramBoolean,
			int[] paramArrayOfInt);

	public static native void endSyncTime(boolean paramBoolean, int paramInt);

	public static void entranceClickEvent(int paramInt) {

	}

	public static native void post(int paramInt);

	public static void setEntranceIcon(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2) {
		setEntranceOneIcon(paramArrayOfByte2);
		setEntranceTwoIcon(paramArrayOfByte1);
	}

	public static native void setEntranceOneIcon(byte[] paramArrayOfByte);

	public static native void setEntranceTwoIcon(byte[] paramArrayOfByte);

	public static native void setIconsPos(float paramFloat1, float paramFloat2,
			float paramFloat3);

	private static Runnable showPayPressDlog = new Runnable() {
		public void run() {
			AlertDialog.Builder localBuilder = new AlertDialog.Builder(act);
			localBuilder.setMessage("点击确定购买按钮表示您同意购买。" + "\n价格" + "" + "20"
					+ "元，不含通讯费，将以短信形式提交订购，并由运营商从话费中扣除相应金额。");
			// localBuilder.setMessage("点击确定购买按钮表示您同意购买。" + "\n价格" + "" +
			// MyPay.itemPriceInfo.get(PayID) +
			// "元，不含通讯费，将以短信形式提交订购，并由运营商从话费中扣除相应金额。");
			localBuilder.setPositiveButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							post(1);
							MainActivity.closeDialog();
						}
					});
			localBuilder.setNegativeButton("确定购买",
					new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface paramAnonymous2DialogInterface,
								int paramAnonymous2Int) {
							RealPay.getSingleton().payAll(act);
							MainActivity.closeDialog();
							GamePay.post(0); 
							
//							  PayUtil.doPay(act, PayID, new Handler() {							  
//							  @Override public void handleMessage(Message msg)
//							  { // MainActivity.closeDialog(); //
//							  GamePay.post(0); super.handleMessage(msg);
//							  MainActivity.closeDialog(); if (msg.what ==
//							  PayUtil.paySuccess) { GamePay.post(0); } else{
//							  SYPay.onPay(act,PayID); } } });
							 
						}
					});
			localBuilder.create();
			mAlertDialog = localBuilder.show();
			mAlertDialog.setCancelable(false);
		}
	};

	public static void SendPayPointClickEvents(Context context, int itemId) {
		// 友盟支付请求统计
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("item", String.valueOf(itemId));
		param.put("channel", Utils.CHANNEL);
		if (itemId >= 0 && itemId <= 13) {
			MobclickAgent.onEvent(context, "point_" + (itemId + 1) + "_click",
					param);
		}

		else if (itemId == 16) {
			MobclickAgent.onEvent(context, "point_15_click", param);
		}
	}
}
