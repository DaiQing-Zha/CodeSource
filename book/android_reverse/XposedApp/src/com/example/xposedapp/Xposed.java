package com.example.xposedapp;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Xposed implements IXposedHookLoadPackage{


	private void hook_method(String className,ClassLoader classLoader,String methodName,Object...parameterTypeAndCallback) {
		try {
			XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypeAndCallback);
		} catch (Exception e) {
			XposedBridge.log(e);
		}
	}
	private void hook_methods(String className,String methodName,XC_MethodHook xmh) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getName().equals(methodName) && !Modifier.isAbstract(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
					XposedBridge.hookMethod(method, xmh);
				}
			}
		} catch (Exception e) {
			XposedBridge.log(e);
		}
	}
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		hook_method("android.telephony.TelephonyManager", lpparam.classLoader, "getDeviceId", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				super.afterHookedMethod(param);
				Object o = param.getResult();
				Log.e("mainHHH","imei = " + o);
				param.setResult("zha000000");
			}
		});
	}

}
