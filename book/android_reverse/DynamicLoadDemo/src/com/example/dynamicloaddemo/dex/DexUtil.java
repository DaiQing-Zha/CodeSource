package com.example.dynamicloaddemo.dex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;


public class DexUtil {

private volatile static DexUtil mDexUtil;
	
    private DexUtil(){} 
    
    private DexClassLoader mDexClassLoader;
    private Class<?> mPayClass;
    private Object mPayObject;
    private Method mObjectSDK,mInitSDK,mPaySDK;
    
    private final String mOriginClass = "com.example.dynamicloaddemo.res.ShowToast";
    private final String mOriginInit = "init";//初始化
    private final String mOriginPay = "doPay";//支付
    private final String mOriginSingle = "getInstance";//单例
    private final String mOriginPath = "payRes.jar";//assets的原始文件
    private final String mOutPath = "/payRes.jar";//file目录下的文件
   
//    private final String mOriginClass = "com.example.test.TestDemo";
//    private final String mOriginInit = "init";//初始化
//    private final String mOriginPay = "doPay";//支付
//    private final String mOriginSingle = "getInstance";//单例
//    private final String mOriginPath = "test.jar";//assets的原始文件
//    private final String mOutPath = "/test.jar";//file目录下的文件
    
    /**
     * 获取类型实例
     * @return
     */
    public static DexUtil getInstance(){
    	if (mDexUtil == null) {
    		synchronized (DexUtil.class) {
				if (mDexUtil == null) {
					mDexUtil = new DexUtil();
				}
			}
		}
    	return mDexUtil;
    }
    
    /**
     * 初始化
     * @param context
     * @param msg
     */
	public boolean init(Context context, String msg) {
		try {
			descryptFile(context);
			String destFilePath = context.getFilesDir().getAbsolutePath() + mOutPath;
			File opFile = new File(destFilePath);
			if (!opFile.exists()) {
				return false;
			}
			if (setObjectDex(context, opFile)) {
				if (!initDex(context.getApplicationContext(), msg)) {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 将assets目录下的资源拷贝到file目录下
	 * @param context
	 * @throws IOException 
	 */
	private boolean descryptFile(Context context) throws IOException{
		File destFile = new File(context.getFilesDir().getAbsolutePath() + mOutPath);
		if (destFile.exists()) {
			long s = 0;
			FileInputStream fis = null;
			fis = new FileInputStream(destFile);
			s = fis.available();
			if (s > 20) {// 文件大小，方式重复拷贝dex文件,20是随便给的
				return true;
			}
		}
		InputStream assetsFileInputStream = null;
		try {
			assetsFileInputStream = context.getAssets().open(mOriginPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		destFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(destFile);
		int readNum = 0;
		while((readNum = assetsFileInputStream.read()) != -1){
			fos.write(readNum);
		}
		fos.close();
		assetsFileInputStream.close();
		return true;
	}
	
	/**
	 * @param context
	 * @param opPath
	 * @return
	 */
	private boolean setObjectDex(Context context,File opPath){
		try {
			mDexClassLoader = new DexClassLoader(opPath.toString()
					, context.getFilesDir().getAbsolutePath()
					, null
					, ClassLoader.getSystemClassLoader().getParent());
			mPayClass = mDexClassLoader.loadClass(mOriginClass);
			mObjectSDK = mPayClass.getMethod(mOriginSingle);
			mPayObject = mObjectSDK.invoke(mPayClass);
		} catch (Exception e) {
			Log.e("mainError", "error = " + e.toString());
			return false;
		}
		return true;
	}
	/**
	 * 动态加载init方法
	 * @param context
	 * @param msg
	 * @return
	 */
	private boolean initDex(Context context,String msg){
		try {
			if (mPayClass != null && mPayObject != null) {
				mInitSDK = mPayClass.getMethod(mOriginInit, new Class[]{Context.class,String.class});
				mInitSDK.invoke(mPayObject, context,msg);
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 支付
	 * @param context
	 * @param money
	 */
	public void doPay(Context context, String money) {
		try {
			if (mPayClass != null && mPayObject != null) {
				mPaySDK = mPayClass.getMethod(mOriginPay, new Class[]{Context.class,String.class});
				mPaySDK.invoke(mPayObject, context,money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
