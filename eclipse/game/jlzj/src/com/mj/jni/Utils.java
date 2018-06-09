package com.mj.jni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;

public class Utils {
	/** Ĭ�������� */
	public static String CHANNEL = "10001";

	public static String DEFAULT_CHANNEL = "10001";

	/** �ж��Ƿ���ͨ��ad sdk ���� �ƹ� */
	public static boolean ISADSDK = false;

	/** �ж��Ƿ���ͨ��ad sdk jar�ƹ� */
	public static boolean ISADSDKJAR = false;

	/** �Ӱ����� */
	public static StringBuilder SUB_PACKAGENAME = new StringBuilder()
			.append("com").append(".").append("ktco");
	/** sdk ����·�� */
	public static String SDK_DHANNEL_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/Download/channel_zmcj";
	/** �Ӱ�����·�� */
	public static String SUB_CHANNEL_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ "/Download/channel_conf";

	public static void StartSubApp(final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String pName;
					List<PackageInfo> pinfo = context.getPackageManager()
							.getInstalledPackages(0);// ��ȡ�����Ѱ�װ����İ���Ϣ
					if (pinfo != null) {
						for (int i = 0; i < pinfo.size(); i++) {
							pName = pinfo.get(i).packageName;
							if (pName.startsWith("com.jkle")
									|| pName.startsWith("com.ktco")) {
								startApp(context, pName);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void startApp(Context context, String pname) {
		try {
			// MobclickAgent.onEvent(context, "sub_start"); // �����Ӱ�ʱ��
			Intent intent1 = new Intent();
			intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ComponentName componentName = new ComponentName(pname, pname
					+ ".MainActivity");
			intent1.setComponent(componentName);
			context.startActivity(intent1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CopyAssertJarToFile(Context context, String fileName,
			String filePath) {
		try {
			InputStream inStream = context.getAssets().open(fileName);
			File newFile = new File(filePath);
			if (!newFile.exists()) {
				// �ļ������ڣ��򴴽�Ŀ¼
				newFile.getParentFile().mkdirs();
			}
			FileOutputStream fs = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int byteread = 0;
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.flush();
			fs.close();
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ���ļ��л�ȡ������ */
	public static String getChannel(String path) {
		StringBuffer buffer = new StringBuffer();
		File file = new File(path);
		try {
			Reader in = new FileReader(file);
			BufferedReader bReader = new BufferedReader(in);
			String string;
			while ((string = bReader.readLine()) != null) {
				buffer.append(string);
			}
			bReader.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "10001";
	}

	/** ��ȡ�������ļ�·�� */
	public static String getChannelPath() {
		// sdk �ƹ������ļ�·��
		return ISADSDK ? SDK_DHANNEL_PATH : SUB_CHANNEL_PATH;
	}

	/**
	 * �ж��Ƿ�װ��ĳ��
	 * 
	 * @return true ��װ��false δ��װ
	 */
	public static boolean installOver(Context context, String packageName) {
		try {
			List<PackageInfo> pinfo = context.getPackageManager()
					.getInstalledPackages(0);// ��ȡ�����Ѱ�װ����İ���Ϣ
			if (pinfo != null) {
				for (int i = 0; i < pinfo.size(); i++) {
					if (pinfo.get(i).packageName.startsWith(packageName)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** ��ȡ�������� 0 δ֪��1 wifi,2 2g */
	public static int getNetworkType(Activity activity) {
		try {
			NetworkInfo localNetworkInfo = ((ConnectivityManager) activity
					.getSystemService(Context.CONNECTIVITY_SERVICE))
					.getActiveNetworkInfo();
			if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {
				if (localNetworkInfo.getType() == 1)
					return 1;
				int i = ((TelephonyManager) activity.getSystemService("phone"))
						.getNetworkType();
				if (i != 0) {
					if (i == 2)
						return 2;
					else if (i == 1)
						return 2;
					return 3;
				}
			}
		} catch (Exception localException) {
			return 0;
		}
		return 0;
	}

	/** ��url�л�ȡ�ļ����� */
	public static String getNameFromUrl(String downloadUrl) {
		String fileName = "";
		String[] a = downloadUrl.split("/");
		fileName = a[a.length - 1];
		return fileName;
	}

	/**
	 * ƴ��path·��
	 */
	public static String getDownloadPath(Activity activity, String path) {
		return getPath(activity) + "/" + path;
	}

	/** ��ȡ�洢��Ŀ¼ */
	public static String getPath(Activity mActivity) {
		String path = null;
		if (hasSDCard()) {
			path = Environment.getExternalStorageDirectory().getPath();
		} else {
			path = mActivity.getFilesDir().toString();
		}
		return path;
	}

	/**
	 * �ж��Ƿ���sdcard
	 * 
	 * @return
	 */
	public static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) ? true : false;
	}

	/** �ж�·��Ϊpath��apk�Ƿ���һ��������apk */
	public static boolean isApk(Context context, String path) {
		File apk = new File(path);
		if (!apk.isFile())
			return false;
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(path,
				PackageManager.GET_ACTIVITIES);
		return info == null ? false : true;
	}

	/** ��װ·��Ϊpath��apk */
	public static void doInstallApp(Context context, String path) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		File file = new File(path);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * ��ȡ�ֻ�����Ӫ��
	 * 
	 * @return 0 δ��ã� 1:�ƶ� 2����ͨ 3������
	 */
	public static int getOperators(Context context) {
		int type = 0;
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// ��ȡSIM����IMSI��
		String imsi = telManager.getSubscriberId();
		// ��Σɣͣɣ��е�MNC
		if (imsi != null) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")
					|| imsi.startsWith("46007")) {// ��Ϊ�ƶ�������46000�µ�IMSI�Ѿ����꣬����������һ��46002��ţ�134/159�Ŷ�ʹ���˴˱��
													// //�й��ƶ�
				type = 1;
			} else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
				// �й���ͨ
				type = 2;
			} else if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
				// �й�����
				type = 3;
			}
		}
		return type;
	}

	/** ��ȡIMEI */
	public static String getImei(Context context) {
		String imei = "";
		imei = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		return imei;
	}

	/** ��ȡIMSI */
	public static String getImsi(Context context) {
		String imsi = "";
		imsi = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		return imsi;
	}

}
