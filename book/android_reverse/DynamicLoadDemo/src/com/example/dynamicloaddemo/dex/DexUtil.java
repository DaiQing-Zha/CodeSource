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
    private final String mOriginInit = "init";//��ʼ��
    private final String mOriginPay = "doPay";//֧��
    private final String mOriginSingle = "getInstance";//����
    private final String mOriginPath = "payRes.jar";//assets��ԭʼ�ļ�
    private final String mOutPath = "/payRes.jar";//fileĿ¼�µ��ļ�
   
//    private final String mOriginClass = "com.example.test.TestDemo";
//    private final String mOriginInit = "init";//��ʼ��
//    private final String mOriginPay = "doPay";//֧��
//    private final String mOriginSingle = "getInstance";//����
//    private final String mOriginPath = "test.jar";//assets��ԭʼ�ļ�
//    private final String mOutPath = "/test.jar";//fileĿ¼�µ��ļ�
    
    /**
     * ��ȡ����ʵ��
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
     * ��ʼ��
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
	 * ��assetsĿ¼�µ���Դ������fileĿ¼��
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
			if (s > 20) {// �ļ���С����ʽ�ظ�����dex�ļ�,20��������
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
	 * ��̬����init����
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
	 * ֧��
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
