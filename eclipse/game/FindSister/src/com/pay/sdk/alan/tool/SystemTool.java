package com.pay.sdk.alan.tool;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

/**
 * 工具类 单例模式获取对象
 * 
 * @author
 * 
 */
public class SystemTool {

	private static SystemTool systemTool;
	private static boolean hasCheckSIMCard = false;
	private static boolean isHasSIMCard = false;
	private static boolean hasCheckEmulator = false;
	private static boolean isRunOnEmulator = false;

	public SystemTool() {
	}

	public static SystemTool getInstance() {
		if (systemTool == null) {
			systemTool = new SystemTool();
		}
		return systemTool;
	}

	/**
	 * umeng初始化
	 * 
	 * @param context
	 */
	public static void UmengAgentOnInit(Context context) {
		if (!isRunOnEmulatorMethod(context) && hasSIMCard(context)) {
			Log.e("umeng", "UMGameAgent init");
//			UMGameAgent.init(context);
		}
	}

	/**
	 * umeng上传
	 * 
	 * @param context
	 */
	public static void UmengAgentOnEvent(Context context, String eventStr) {
		if (!isRunOnEmulatorMethod(context) && hasSIMCard(context)) {
			Log.e("umeng", "UmengAgentOnEvent");
			MobclickAgent.onEvent(context, eventStr);
//			Toast.makeText(context, "UmengAgentOnEvent", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * umeng暂停
	 * 
	 * @param context
	 */
	public static void UmengAgentOnPause(Context context) {
		if (!isRunOnEmulatorMethod(context) && hasSIMCard(context)) {
			Log.e("umeng", "UmengAgentOnPause");
			MobclickAgent.onPause(context);
		}
	}

	/**
	 * umeng恢复
	 * 
	 * @param context
	 */
	public static void UmengAgentOnResume(Context context) {
//		Toast.makeText(context, "onResume1 " + hasSIMCard(context) + " " + !isRunOnEmulatorMethod(context), Toast.LENGTH_SHORT).show();
		if (!isRunOnEmulatorMethod(context) && hasSIMCard(context)) {
			Log.e("umeng", "UmengAgentOnResume");
			MobclickAgent.onResume(context);
//			Toast.makeText(context, "onResume2", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 检测是否运行在虚拟机上
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isRunOnEmulatorMethod(Context context) {
		return false;
//		if (!hasCheckEmulator) {
//			if (AntiEmulator.CheckDeviceIDS(context)
//					|| AntiEmulator.CheckEmulatorBuild(context)
//					|| AntiEmulator.CheckEmulatorFiles()
//					|| AntiEmulator.CheckImsiIDS(context)
//					|| AntiEmulator.CheckOperatorNameAndroid(context)
//					|| AntiEmulator.CheckPhoneNumber(context)
//					|| AntiEmulator.checkPipes()
//					|| AntiEmulator.checkQEmuDriverFile()) {
//				isRunOnEmulator = true;
//			} else {
//				isRunOnEmulator = false;
//			}
//			hasCheckEmulator = true;
//		}
//		return isRunOnEmulator;
	}

	/**
	 * 获得meta中的值
	 * 
	 * @param context
	 * @param metaStrName
	 * @return
	 */
	public static String getMateDataStr(Context context, String metaStrName) {
		try {
			
			return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128)
					.metaData.get(metaStrName)
					.toString()
					.trim();
		} catch (Exception localException) {
		}
		return "";
	}

	/**
	 * 是否有sim卡
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasSIMCard(Context context) {
		if (!hasCheckSIMCard) {
			TelephonyManager telMgr = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			int simState = telMgr.getSimState();
			boolean result = true;
			switch (simState) {
			case TelephonyManager.SIM_STATE_ABSENT:
				result = false; // 没有SIM卡
				break;
			case TelephonyManager.SIM_STATE_UNKNOWN:
				result = false;
				break;
			}
			Log.e("result", result ? "有SIM卡" : "无SIM卡");
			isHasSIMCard = result;
			hasCheckSIMCard = true;
		}
		return isHasSIMCard;
	}

	/**
	 * 获得唯一不可变的设备uuid
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceUUID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, tmPhone, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		Log.d("debug", "uuid=" + uniqueId);
		return uniqueId;
	}

	/**
	 * 获得可变的uuid,用于订单串号
	 * 
	 * @return
	 */
	public static String getNowUUID() {
		UUID uuid = UUID.randomUUID();
		String uniqueId = uuid.toString();
		Log.d("debug", "----->UUID" + uuid);
		return uniqueId;
	}

	/*
	 * 
	 * 在桌面添加快捷方式
	 * 
	 * @param activity 界面
	 * 
	 * @param iconBmp 快捷方式图标
	 * 
	 * @param name 快捷方式名称
	 * 
	 * @param uri 快捷方式的intent Uri
	 */
	public static void addShortcut(final Activity activity, Bitmap iconBmp,
			String name, String uriPath) {
		try {
			System.out.println("addShortcut");
			String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

			Uri uri = Uri.parse(uriPath);

			Intent intentAddShortcut = new Intent(ACTION_ADD_SHORTCUT);
			// 不允许重复创建
			intentAddShortcut.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的

			// 添加名称
			intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

			// 添加图标
			/*
			 * intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
			 * Intent.ShortcutIconResource.fromContext(activity,
			 * R.drawable.icon_s));
			 */
			intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconBmp);

			// 设置Launcher的Uri数据

			Intent intentLauncher = new Intent();

			intentLauncher.setData(uri);

			// 添加快捷方式的启动方法
			intentAddShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,intentLauncher);

			activity.sendBroadcast(intentAddShortcut);

		} catch (Exception ex) {
			System.out.println("addShortcut err=" + ex.toString());
		}

	}

	/**
	 * String数组转int数组
	 * **/
	public static int[] string2Ints(String[] sa) {
		int[] n = new int[sa.length];
		for (int i = 0; i < sa.length; i++) {
			n[i] = Integer.parseInt(sa[i]);
		}
		return n;
	}
 
}
