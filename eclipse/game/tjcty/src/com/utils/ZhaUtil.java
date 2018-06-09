package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class ZhaUtil {

	/**
     * 是否是真机并且有sim卡
     * @return
     */
    public static boolean isPrototypeAndHaveSim(Context context){
    	TelephonyManager telMgr = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        boolean isHaveSim = true;//是否有sim卡
        String imsi = telMgr.getSubscriberId(); // 取出IMSI  
        if (imsi == null || imsi.length() <= 0) {  
            isHaveSim = false;
        } else {  
            isHaveSim = true;
        }  
        if (isHaveSim && !checkIsNotRealPhone(context)) {
			return true;
		}else {
			return false;
		}
    }

    /**
     * 判断cpu是否为电脑、以及序列号serial、以及mac地址，来判断 模拟器
     * @return true 为模拟器
     */
    private static boolean checkIsNotRealPhone(Context context) {
        String cpuInfo = readCpuInfo();
        if ((cpuInfo.contains("intel") || cpuInfo.contains("amd"))) {
            return true;
        }
        String serial = android.os.Build.SERIAL;
        Log.d("mainHHH", "serial = " + serial);
        if ("unknown".equals(serial)) {
			return true;
		}
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        WifiInfo winfo = wifi.getConnectionInfo();  
        String mac =  winfo.getMacAddress();  
        if (mac == null) {
			return true;
		}
        return false;
    }
    /**
     * 读取cpu信息
     * @return
     */
    private static String readCpuInfo() {
        String result = "";
        try {
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            ProcessBuilder cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            StringBuffer sb = new StringBuffer();
            String readLine = "";
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine);
            }
            responseReader.close();
            result = sb.toString().toLowerCase();
        } catch (IOException ex) {
        }
        return result;
    }
}
