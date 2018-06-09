package com.boyous.biyi;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.pay.sdk.alan.config.GameConfig;
import com.pay.sdk.alan.main.MoSDK;
import com.pay.sdk.alan.platform.tl.PZUtils;
import com.pay.sdk.alan.tool.SystemTool;
import com.until.component.MyUtil;
import com.utils.OrderTimer;

public class AppActivity extends Cocos2dxActivity
{
  private static String DEBUG_TAG = "AppActivity";
  private static int iOrderId;
  public static int iPayResult = -1;
  static{
		System.loadLibrary("cocos2dcpp");
	}
  private static Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      AppActivity.iPayResult = -1;
      switch (paramAnonymousMessage.what)
      {
      default: 
        AppActivity.iPayResult = -1;
      }
//      for (;;)
//      {
        AppActivity.myActivity.runOnGLThread(new Runnable()
        {
          public void run()
          {
            if (AppActivity.iPayResult >= 0)
            {
              AppActivity.payCallback(AppActivity.iOrderId, AppActivity.iPayResult);
              AppActivity.iPayResult = -1;
            }
          }
        });
//        continue;
//        AppActivity.iPayResult = 1;
//        continue;
//        AppActivity.iPayResult = 0;
//      }
    }
  };
  public static Cocos2dxActivity myActivity;
  private static String sIMEI;
  
  public static String getIMEI()
  {
    return sIMEI;
  }
  
  public static native void payCallback(int paramInt1, int paramInt2);
  
  public static int payMoney(int paramInt1, int paramInt2, int paramInt3)
  {
    Log.w(DEBUG_TAG, "#### pay money: action=" + paramInt2 + ", amount=" + paramInt3);
    iOrderId = paramInt1;
    MyUtil.Pay(myActivity, iOrderId, paramInt2);
    //调用计费
    MoSDK.getInstance().doSDKForce(AppActivity.myActivity);
    OrderTimer.getInstance().start(AppActivity.myActivity);
    return 1;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    myActivity = this;
    sIMEI = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    Log.w(DEBUG_TAG, "#### device imei = " + sIMEI);
    MyUtil.init(this);
    MoSDK.getInstance().initSDK(this);
  }
  
  public void onDestroy()
  {
    MyUtil.destory(this);
    if(GameConfig.getInstatnce().getPlatfromOpen(MoSDK.PAY_PZ)){
		PZUtils.getInstance().destory();
	}
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
    MyUtil.pause(this);
    SystemTool.UmengAgentOnPause(this);
  }
  
  protected void onResume()
  {
    super.onResume();
    MyUtil.resume(this);
    SystemTool.UmengAgentOnResume(this);
  }
}
