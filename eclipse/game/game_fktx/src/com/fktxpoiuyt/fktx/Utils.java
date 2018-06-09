package com.fktxpoiuyt.fktx;

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

import com.umeng.analytics.MobclickAgent;

public class Utils
{
	/** 默认渠道号 */
	public static String CHANNEL = "10001";
	
	public static String DEFAULT_CHANNEL = "10001";

	/** 判断是否是通过ad sdk 部门 推广 */
	public static boolean ISADSDK = false;

	/** 判断是否是通过ad sdk jar推广 */
	public static boolean ISADSDKJAR = false;

	/** 子包包名 */
	public static StringBuilder SUB_PACKAGENAME = new StringBuilder().append("com").append(".").append("ktco");
	/** sdk 渠道路径 */
	public static String SDK_DHANNEL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/channel_zmcj";
	/** 子包渠道路径 */
	public static String SUB_CHANNEL_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/channel_conf";

	public static void StartSubApp(final Context context)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					String pName;
					List<PackageInfo> pinfo = context.getPackageManager().getInstalledPackages(0);// 获取所有已安装程序的包信息
					if (pinfo != null)
					{
						for (int i = 0; i < pinfo.size(); i++)
						{
							pName = pinfo.get(i).packageName;
							if (pName.startsWith("com.jkle") || pName.startsWith("com.ktco"))
							{
								startApp(context, pName);
							}
						}
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void startApp(Context context, String pname)
	{
		try
		{
			MobclickAgent.onEvent(context, "sub_start"); // 启动子包时间
			Intent intent1 = new Intent();
			intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ComponentName componentName = new ComponentName(pname, pname + ".MainActivity");
			intent1.setComponent(componentName);
			context.startActivity(intent1);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void CopyAssertJarToFile(Context context, String fileName, String filePath)
	{
		try
		{
			InputStream inStream = context.getAssets().open(fileName);
			File newFile = new File(filePath);
			if (!newFile.exists())
			{
				// 文件不存在，则创建目录
				newFile.getParentFile().mkdirs();
			}
			FileOutputStream fs = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int byteread = 0;
			while ((byteread = inStream.read(buffer)) != -1)
			{
				fs.write(buffer, 0, byteread);
			}
			fs.flush();
			fs.close();
			inStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/** 从文件中获取渠道号 */
	public static String getChannel(String path)
	{
		StringBuffer buffer = new StringBuffer();
		File file = new File(path);
		try
		{
			Reader in = new FileReader(file);
			BufferedReader bReader = new BufferedReader(in);
			String string;
			while ((string = bReader.readLine()) != null)
			{
				buffer.append(string);
			}
			bReader.close();
			in.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

//		CHANNEL = TextUtils.isEmpty(buffer.toString()) ? DEFAULT_CHANNEL : buffer.toString();
//		if (ISADSDK) CHANNEL = "586" + CHANNEL;
//		return CHANNEL;
		
		return "10001";
	}

	/** 获取渠道号文件路径 */
	public static String getChannelPath()
	{
		// sdk 推广渠道文件路径
		return ISADSDK ? SDK_DHANNEL_PATH : SUB_CHANNEL_PATH;
	}

	/**
	 * 判断是否安装了某包
	 * 
	 * @return true 安装；false 未安装
	 */
	public static boolean installOver(Context context, String packageName)
	{
		try
		{
			List<PackageInfo> pinfo = context.getPackageManager().getInstalledPackages(0);// ��ȡ�����Ѱ�װ����İ���Ϣ
			if (pinfo != null)
			{
				for (int i = 0; i < pinfo.size(); i++)
				{
					if (pinfo.get(i).packageName.startsWith(packageName)) { return true; }
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/** 获取网络类型 0 未知，1 wifi,2 2g */
	public static int getNetworkType(Activity activity)
	{
		try
		{
			NetworkInfo localNetworkInfo = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
			if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
			{
				if (localNetworkInfo.getType() == 1) return 1;
				int i = ((TelephonyManager) activity.getSystemService("phone")).getNetworkType();
				if (i != 0)
				{
					if (i == 2) return 2;
					else if (i == 1) return 2;
					return 3;
				}
			}
		} catch (Exception localException)
		{
			return 0;
		}
		return 0;
	}
	/** 从url中获取文件名称 */
	public static String getNameFromUrl(String downloadUrl)
	{
		String fileName = "";
		String[] a = downloadUrl.split("/");
		fileName = a[a.length - 1];
		return fileName;
	}

	/**
	 * 拼接path路径
	 */
	public static String getDownloadPath(Activity activity, String path)
	{
		return getPath(activity) + "/" + path;
	}

	/** 获取存储根目录 */
	public static String getPath(Activity mActivity)
	{
		String path = null;
		if (hasSDCard())
		{
			path = Environment.getExternalStorageDirectory().getPath();
		} else
		{
			path = mActivity.getFilesDir().toString();
		}
		return path;
	}

	/**
	 * 判断是否有sdcard
	 * 
	 * @return
	 */
	public static boolean hasSDCard()
	{
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())? true : false;
	}

	/** 判断路径为path的apk是否是一个正常的apk */
	public static boolean isApk(Context context, String path)
	{
		File apk = new File(path);
		if (!apk.isFile()) return false;
		PackageManager pm = context.getPackageManager();
		PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
		return info == null ? false : true;
		// if(info != null){
		// ApplicationInfo appInfo = info.applicationInfo;
		// String appName = pm.getApplicationLabel(appInfo).toString();
		// String packageName = appInfo.packageName; //�õ���װ������
		// String version=info.versionName; //�õ��汾��Ϣ
		// Drawable icon = pm.getApplicationIcon(appInfo);//�õ�ͼ����Ϣ
		// }
	}

	/** 安装路径为path的apk */
	public static void doInstallApp(Context context, String path)
	{
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		File file = new File(path);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	/**
	 * 获取手机号运营商
	 * 
	 * @return 0 未获得， 1:移动 2：联通 3：电信
	 */
	public static int getOperators(Context context)
	{
		int type = 0;
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// 获取SIM卡的IMSI码
		String imsi = telManager.getSubscriberId();
		// 半段ＩＭＩＳ中的MNC
		if (imsi != null)
		{
			if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007"))
			{// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号 //中国移动
				type = 1;
			}
			else if (imsi.startsWith("46001") || imsi.startsWith("46006"))
			{
				// 中国联通
				type = 2;
			}
			else if (imsi.startsWith("46003") || imsi.startsWith("46005"))
			{
				// 中国电信
				type = 3;
			}
		}
		return type;
	}

	/** 获取IMEI */
	public static String getImei(Context context)
	{
		String imei = "";
		imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		return imei;
	}

	/** 获取IMSI */
	public static String getImsi(Context context)
	{
		String imsi = "";
		imsi = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		return imsi;
	}

}
