package com.meiqu.beauty.abc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

public class i {
    public static String a(String arg6, String arg7) {
        String v1 = "";
        try {
            URLConnection v0_1 = new URL(arg6).openConnection();
            ((HttpURLConnection)v0_1).setDoOutput(true);
            ((HttpURLConnection)v0_1).setDoInput(true);
            ((HttpURLConnection)v0_1).setRequestMethod("POST");
            ((HttpURLConnection)v0_1).setUseCaches(false);
            ((HttpURLConnection)v0_1).setInstanceFollowRedirects(false);
            ((HttpURLConnection)v0_1).setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            ((HttpURLConnection)v0_1).setRequestProperty("Accept", "*/*");
            ((HttpURLConnection)v0_1).connect();
            if(!i.a(arg7).booleanValue()) {
                DataOutputStream v2 = new DataOutputStream(((HttpURLConnection)v0_1).getOutputStream());
                byte[] v3 = arg7.getBytes("utf-8");
                v2.write(v3, 0, v3.length);
                v2.flush();
                v2.close();
            }

            BufferedReader v2_1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)v0_1).getInputStream(), "UTF-8"));
            while(true) {
                String v3_1 = v2_1.readLine();
                if(v3_1 == null) {
                    break;
                }

                v1 = v1 + v3_1;
            }

            v2_1.close();
            ((HttpURLConnection)v0_1).disconnect();
            System.out.println(v1);
            return v1;
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
        return v1;
    }

    public static String a(Date arg1, String arg2) {
        String v0_1;
        try {
            v0_1 = new SimpleDateFormat(arg2).format(arg1);
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_1 = "";
        }

        return v0_1;
    }

    public static String a(String arg4, String arg5, String arg6) {
        String v0_1 =
		null;
//        String v1 = null;
//        if(arg4 != null && !"".equals(arg4.trim())) {
//            try {
//                v0_1 = new String(a.b(Base64.decode(arg4, 0), arg5, arg6), "UTF-8");
//            }
//            catch(Exception v0) {
//                v0.printStackTrace();
//                v0_1 = v1;
//            }
//        }
//        else {
//            v0_1 = v1;
//        }
//
        return v0_1;
    }

    public static String a(Context arg1) {
        return ((TelephonyManager) arg1.getSystemService("phone")).getSubscriberId();
    }

    public static String a() {
        return Build.VERSION.RELEASE;
    }

    public static Boolean a(String arg1) {
        boolean v0 = arg1 == null || (arg1.isEmpty()) ? true : false;
        return Boolean.valueOf(v0);
    }

    public static int a(Context arg2, float arg3) {
        return ((int)(arg2.getResources().getDisplayMetrics().density * arg3 + 0.5f));
    }

    public static int a(JSONObject arg1, String arg2) {
        int v0_1;
        try {
            v0_1 = arg1.getInt(arg2);
        }
        catch(Exception v0) {
            v0_1 = 0;
        }

        return v0_1;
    }

    public static Object a(Object arg4, int arg5) {
        int v1 = Array.getLength(arg4);
        if(arg5 >= 0 && arg5 < v1) {
            Object v0 = Array.newInstance(arg4.getClass().getComponentType(), v1 - 1);
            System.arraycopy(arg4, 0, v0, 0, arg5);
            if(arg5 < v1 - 1) {
                System.arraycopy(arg4, arg5 + 1, v0, arg5, v1 - arg5 - 1);
            }

            arg4 = v0;
        }

        return arg4;
    }

    public static String b(String arg2, String arg3, String arg4) {
        String v0_1 =
		null;
//        try {
//            v0_1 = Base64.encodeToString(a.a(arg2.getBytes("UTF-8"), arg3, arg4), 0);
//        }
//        catch(Exception v0) {
//            v0.printStackTrace();
//            v0_1 = null;
//        }
//
        return v0_1;
    }

    public static String b(String arg6) {
        String v0 = "";
        try {
            MessageDigest v1_1 = MessageDigest.getInstance("MD5");
            v1_1.update(arg6.getBytes());
            byte[] v3 = v1_1.digest();
            StringBuffer v4 = new StringBuffer("");
            int v2;
            for(v2 = 0; v2 < v3.length; ++v2) {
                int v1_2 = v3[v2];
                if(v1_2 < 0) {
                    v1_2 += 256;
                }

                if(v1_2 < 16) {
                    v4.append("0");
                }

                v4.append(Integer.toHexString(v1_2));
            }

            v0 = v4.toString();
        }
        catch(NoSuchAlgorithmException v1) {
            v1.printStackTrace();
        }

        return v0;
    }

    public static String b(Context arg1) {
        return ((TelephonyManager) arg1.getSystemService("phone")).getDeviceId();
    }

    public static String b() {
        return Build.MODEL;
    }

    public static long b(JSONObject arg2, String arg3) {
        long v0_1;
        try {
            v0_1 = arg2.getLong(arg3);
        }
        catch(Exception v0) {
            v0_1 = 0;
        }

        return v0_1;
    }

    public static String c() {
        return Long.toString(((long)(((((double)System.currentTimeMillis())) + Math.random()) * 1000)), 36);
    }

    public static String c(Context arg5) {
        SharedPreferences v2 = arg5.getSharedPreferences("DeviceInfo", 0);
        String v0 = v2.getString("DeviceId", "");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            String v1 = i.b(arg5);
            v0 = ((WifiManager) arg5.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if(v1 != null) {
                if(v1.length() != 14 && v1.length() != 15) {
                	v0 = v0.replace(":", "");
                }

                Matcher v3 = Pattern.compile("^(?:([0-9a-zA-Z])\\1{3})").matcher(((CharSequence)v1));
                Matcher v4 = Pattern.compile("(?:([0-9a-zA-Z])\\1{5})$").matcher(((CharSequence)v1));
                if(!v3.find() && !v4.find()) {
                    v0 = v1;
                    if(TextUtils.isEmpty(((CharSequence)v0))) {
                        v0 = i.c();
                    }

                    SharedPreferences.Editor v1_1 = v2.edit();
                    v1_1.putString("DeviceId", v0);
                    v1_1.commit();
                }

                v0 = v0.replace(":", "");
            }
            else {
//            label_41:
                v0 = v0.replace(":", "");
            }

//        label_33:
//            if(TextUtils.isEmpty(((CharSequence)v0))) {
//                v0 = i.c();
//            }
//
//            SharedPreferences.Editor v1_1 = v2.edit();
//            v1_1.putString("DeviceId", v0);
//            v1_1.commit();
        }

        return v0;
    }

    public static String c(String arg2) {
        String[] v0 = arg2.split("/");
        return v0[v0.length - 1];
    }

    public static String c(JSONObject arg1, String arg2) {
        String v0_1;
        try {
            v0_1 = arg1.getString(arg2);
        }
        catch(Exception v0) {
            v0_1 = null;
        }

        return v0_1;
    }

    public static String d(Context arg1) {
        return ((TelephonyManager) arg1.getSystemService("phone")).getSimSerialNumber();
    }

    public static JSONObject d(JSONObject arg1, String arg2) {
        JSONObject v0_1;
        try {
            v0_1 = arg1.getJSONObject(arg2);
        }
        catch(Exception v0) {
            v0_1 = null;
        }

        return v0_1;
    }

    public static int e(Context arg2) {
        int v0 = ((TelephonyManager) arg2.getSystemService("phone")).getSimState() == 1 ? 0 : 1;
        return v0;
    }

    public static JSONArray e(JSONObject arg1, String arg2) {
        JSONArray v0_1;
        try {
            v0_1 = arg1.getJSONArray(arg2);
        }
        catch(Exception v0) {
            v0_1 = null;
        }

        return v0_1;
    }

    public static String f(Context arg2) {
        String v0_1;
        NetworkInfo v0 = ((ConnectivityManager) arg2.getSystemService("connectivity")).getActiveNetworkInfo();
        if(v0 == null) {
            v0_1 = "UNKNOWN";
        }
        else if(v0.getType() == 1) {
            v0_1 = "WIFI";
        }
        else {
            v0_1 = "GPRS";
        }

        return v0_1;
    }

    public static int g(Context arg5) {
        CellLocation v0_2 =
		null;
        CellLocation v2 = null;
        Object v0 = arg5.getSystemService("phone");
        if(((TelephonyManager)v0).getPhoneType() == 2) {
            try {
                v0_2 = ((TelephonyManager)v0).getCellLocation();
                if(v0_2 == null) {
                    return 0;
                }
            }
            catch(Exception v0_1) {
            	v0_1.printStackTrace();
                v0_2 = v2;
                if(v0_2 == null) {
                    return 0;
                }
            }
        }

        int v0_3 = 0;
        if(((TelephonyManager)v0).getPhoneType() == 1) {
            try {
                v0_2 = ((TelephonyManager)v0).getCellLocation();
                if(v0_2 == null) {
                    v0_3 = 0;
                }
                else {
                    return ((GsmCellLocation)v0_2).getLac();
            }}
            catch(Exception v0_1) {
            	v0_1.printStackTrace();
                v0_2 = v2;
            }
        }


        return ((CdmaCellLocation)v0_2).getNetworkId();
    }

    public static int h(Context arg5) {
    	int v0_3 = 0;
        CellLocation v0_2;
        CellLocation v2 = null;
        Object v0 = arg5.getSystemService("phone");
        if(((TelephonyManager)v0).getPhoneType() == 2) {
            try {
                v0_2 = ((TelephonyManager)v0).getCellLocation();
                if(v0_2 == null) {
                    v0_3 = 0;
                    return v0_3;
                }

                v0_3 = ((CdmaCellLocation)v0_2).getBaseStationId();
                return v0_3;
            }
            catch(Exception v0_1) {
            	 v0_1.printStackTrace();
                 v0_2 = v2;
            }
        }

        if(((TelephonyManager)v0).getPhoneType() == 1) {
            try {
                v0_2 = ((TelephonyManager)v0).getCellLocation();
                if(v0_2 == null) {
                    v0_3 = 0;
                }
                else {
                    v0_3 = ((GsmCellLocation)v0_2).getCid();
                   return v0_3;
                }
            }
            catch(Exception v0_1) {
                v0_1.printStackTrace();
            }
        }
        return v0_3;

    }

    public static String i(Context arg3) {
        String v0_1;
        try {
            v0_1 = arg3.getPackageManager().getPackageInfo(arg3.getPackageName(), 0).versionName;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_1 = "1.0.0";
        }

        return v0_1;
    }

    public static int j(Context arg3) {
        int v0_1;
        try {
            v0_1 = arg3.getPackageManager().getPackageInfo(arg3.getPackageName(), 0).versionCode;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_1 = 1;
        }

        return v0_1;
    }

    public static String k(Context arg3) {
        String v0_1;
        try {
            v0_1 = String.valueOf(arg3.getPackageManager().getApplicationInfo(arg3.getPackageName(), 128).metaData.get("UMENG_CHANNEL"));
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_1 = "40001";
        }

        return v0_1;
    }

    public static String l(Context arg3) {
        String v0_1;
        try {
            v0_1 = String.valueOf(arg3.getPackageManager().getApplicationInfo(arg3.getPackageName(), 128).metaData.get("MQAPPID"));
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v0_1 = "7012416";
        }

        return v0_1;
    }
}
