package com.until.component;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
//import com.amaz.onib.Data;
//import com.amaz.onib.Restl;
//import com.amaz.onib.Utils;
//import com.amaz.onib.Utils.PingListener;
//import com.android.zaLY.RPegpN.MResultListener;
//import com.android.zaLY.RPegpN.MU2tw;
//import com.lotuseed.android.Lotuseed;
//import com.mgame.pay.IPayCallback;
//import com.mgame.pay.main.Payment;
//import com.pay.pay.InitListener;
//import com.pay.pay.InitResult;
//import com.pay.pay.PayListener;
//import com.pay.pay.PayResult;
//import com.pay.pay.WYZFPay;
//import com.payment.plus.main.MaySDK;
//import com.payment.plus.main.OnPayListener;
//import com.tendcloud.tenddata.TCAgent;
//import com.wps.pay.frame.IPaySDK;
//import com.wps.pay.frame.PaySDK;
//import com.wps.pay.frame.callback.InitResultCallback;
//import com.wps.pay.frame.callback.PayResultCallback;
//import com.wps.pay.frame.data.ParamsEntity;
//import com.y.f.jar.pay.BillingListener;
//import com.y.f.jar.pay.YFPaySDK;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MyUtil
{
  private static String TAG = "MyUtil";
  public static final int YL_REQUEST_TYPE = 0;
  private static String mCporderid;
  private static String mDmGid;
  private static String mJmChild;
  private static int mKqPid;
  private static int mPayIndex;
//  private static Utils mPzPay;
  private static int mPzPrice;
  private static String mTKPointID;
  private static int mTKPrice;
  private static String mTYPayCode;
  private static String mTYPrice;
  private static String mTitle = "";
//  public static IPayCallback mTyBillingListener = new IPayCallback()
//  {
//    public void onBillingResult(int paramAnonymousInt, String paramAnonymousString)
//    {
//      switch (paramAnonymousInt)
//      {
//      case 2023: 
//      default: 
//        return;
//      }
//      MyUtil.showToast("TY Succeed", MyContanst.DEBUG);
//      paramAnonymousString = new HashMap();
//      paramAnonymousString.put("goodName", MyUtil.mTitle);
//      MyUtil.onEventStatistical(MyUtil.m_Context, "TYSucceed", paramAnonymousString);
//    }
//    
//    public void onInitResult(int paramAnonymousInt, String paramAnonymousString) {}
//  };
  private static String mYLGoodID;
  private static int mYLPrice;
  private static String mYfPayCode;
  private static String mYfPrice;
  private static String mZZPointID;
  private static String mZZPrice;
  private static Activity m_Context;
  private static MyUtil m_Instance;
//  private static YFPaySDK mjBilling;
  public static Handler myHandler;
  private static Handler payHandler;
  public static int payPoint;
  private boolean bPNEPop = false;
  private String mDESC = "";
  private int mYJPrice;
  private String propID;
  
  static
  {
//    mPayIndex = 0;
//    myHandler = new Handler()
//    {
//      public void handleMessage(Message paramAnonymousMessage)
//      {
//        switch (paramAnonymousMessage.what)
//        {
//        default: 
//          return;
//        }
//        try
//        {
//          MyUtil.access$0();
//        }
//        catch (Exception paramAnonymousMessage)
//        {
//          try
//          {
//            MyUtil.access$1();
//          }
//          catch (Exception paramAnonymousMessage)
//          {
//            try
//            {
//              MyUtil.doDMPay();
//            }
//            catch (Exception paramAnonymousMessage)
//            {
//              try
//              {
//                MyUtil.doWYPay();
//              }
//              catch (Exception paramAnonymousMessage)
//              {
//                try
//                {
//                  for (;;)
//                  {
//                    MyUtil.access$2();
//                    try
//                    {
//                      MyUtil.access$3();
//                      return;
//                    }
//                    catch (Exception paramAnonymousMessage)
//                    {
//                      paramAnonymousMessage.printStackTrace();
//                      return;
//                    }
//                    paramAnonymousMessage = paramAnonymousMessage;
//                    paramAnonymousMessage.printStackTrace();
//                    continue;
//                    paramAnonymousMessage = paramAnonymousMessage;
//                    paramAnonymousMessage.printStackTrace();
//                    continue;
//                    paramAnonymousMessage = paramAnonymousMessage;
//                    paramAnonymousMessage.printStackTrace();
//                    continue;
//                    paramAnonymousMessage = paramAnonymousMessage;
//                    paramAnonymousMessage.printStackTrace();
//                  }
//                }
//                catch (Exception paramAnonymousMessage)
//                {
//                  for (;;)
//                  {
//                    paramAnonymousMessage.printStackTrace();
//                  }
//                }
//              }
//            }
//          }
//        }
//      }
//    };
  }
  
  public static void Pay(Context paramContext, int paramInt1, int paramInt2)
  {
    mPayIndex = paramInt1;
    getInstance().doPay(paramContext, Integer.toString(paramInt2));
  }
  
  public static void destory(Context paramContext)
  {
    getInstance().dodestory(paramContext);
  }
  
  public static void doDMPay()
  {
    long l = System.currentTimeMillis();
//    MU2tw.getInstance().star(m_Context, mDmGid, String.valueOf(l), "ext", new MResultListener()
//    {
//      public void callback(String paramAnonymousString1, int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString2)
//      {
//        if (paramAnonymousInt1 == 1001)
//        {
//          MyUtil.showToast("DM订购成功", MyContanst.DEBUG);
//          paramAnonymousString1 = new HashMap();
//          paramAnonymousString1.put("goodName", MyUtil.mTitle);
//          MyUtil.onEventStatistical(MyUtil.m_Context, "DMSucceed", paramAnonymousString1);
//          return;
//        }
//        MyUtil.showToast("DM订购失败:detail:" + paramAnonymousInt2, MyContanst.DEBUG);
//      }
//    });
  }
  
  private static void doPZPay()
  {
//    MyLogger.i(TAG, "doPZPay");
//    MyLogger.i(TAG, "cporderid:" + mCporderid);
//    showToast("PZ，ing..", MyContanst.DEBUG);
//    mPzPay.start(mPzPrice, mCporderid, "");
  }
  
  private void doPzInit(Context paramContext)
  {
//    MyLogger.i(TAG, "PZ_CHANNEL:" + MyApplication.mApplicationChannel);
//    mPzPay = Utils.getInstanct(paramContext, "488", MyApplication.mApplicationChannel, new Utils.PingListener()
//    {
//      public void onFinished(boolean paramAnonymousBoolean, Restl paramAnonymousRestl)
//      {
//        if ((paramAnonymousBoolean) && (paramAnonymousRestl != null))
//        {
//          MyUtil.showToast("PZ succeed", MyContanst.DEBUG);
//          Lotuseed.onEvent("PZ_Succeed");
//          return;
//        }
//        MyUtil.showToast("PZ error，失败码：" + paramAnonymousRestl.status, MyContanst.DEBUG);
//      }
//      
//      public void onPing(List<Data> paramAnonymousList) {}
//    });
  }
  
  private static void doSYPay()
  {
//    MyLogger.i(TAG, "doSYPay");
//    showToast("SY，ing..", MyContanst.DEBUG);
//    PaySDK.getInstance().startPay(m_Context, payPoint, 0, 0, new PayResultCallback()
//    {
//      public void onPayCancel(int paramAnonymousInt) {}
//      
//      public void onPayFailed(int paramAnonymousInt, String paramAnonymousString)
//      {
//        MyUtil.showToast("sy error，失败码：" + paramAnonymousInt + ",失败信息：" + paramAnonymousString, MyContanst.DEBUG);
//      }
//      
//      public void onPaySuccess(int paramAnonymousInt1, int paramAnonymousInt2)
//      {
//        MyUtil.showToast("sy succeed", MyContanst.DEBUG);
//      }
//    });
  }
  
  private void doSyInit(Context paramContext)
  {
//    paramContext = new ParamsEntity();
//    paramContext.setAppId("100181");
//    paramContext.setMerchantId("10034");
//    paramContext.setMerchantPasswdId("8B8506FC2EF4C8B6045A936859A3D295");
//    paramContext.setChannelId("a" + MyApplication.mApplicationChannel);
//    paramContext.setSubChannelId("a" + MyApplication.mApplicationChannel);
//    PaySDK.getInstance().initSDK(m_Context.getApplication(), MyContanst.appName, paramContext, new InitResultCallback()
//    {
//      public void onCallback(boolean paramAnonymousBoolean, int paramAnonymousInt, String paramAnonymousString)
//      {
//        if (paramAnonymousBoolean)
//        {
//          MyUtil.showToast("sy，初始化成功", MyContanst.DEBUG);
//          return;
//        }
//        MyUtil.showToast("sy，初始化失败:返回码:" + paramAnonymousInt + ",返回信息：" + paramAnonymousString, MyContanst.DEBUG);
//      }
//    });
  }
  
  private static void doTYPay()
  {
//    Payment.getInstance().pay(m_Context, mTYPayCode, mTYPrice, mTyBillingListener);
  }
  
  public static void doWYPay()
  {
//    WYZFPay.pay(m_Context, 70347266, 2000, new PayListener()
//    {
//      public void onPayResult(PayResult paramAnonymousPayResult)
//      {
//        if (paramAnonymousPayResult == PayResult.SUCCESS)
//        {
//          paramAnonymousPayResult = new HashMap();
//          paramAnonymousPayResult.put("goodName", MyUtil.mTitle);
//          MyUtil.onEventStatistical(MyUtil.m_Context, "WYSucceed", paramAnonymousPayResult);
//        }
//      }
//    });
  }
  
  private void doYFInit(Context paramContext)
  {
//    mjBilling = new YFPaySDK(m_Context, new BillingListener()
//    {
//      public void onBillingResult(int paramAnonymousInt, Bundle paramAnonymousBundle)
//      {
//        switch (paramAnonymousInt)
//        {
//        default: 
//          MyUtil.showToast("yf error，失败码：" + paramAnonymousInt + ",失败信息：" + paramAnonymousBundle, MyContanst.DEBUG);
//          return;
//        }
//        MyUtil.showToast("yf succeed", MyContanst.DEBUG);
//        paramAnonymousBundle = new HashMap();
//        paramAnonymousBundle.put("goodName", MyUtil.mTitle);
//        MyUtil.onEventStatistical(MyUtil.m_Context, "YFSucceed", paramAnonymousBundle);
//      }
//      
//      public void onInitResult(int paramAnonymousInt) {}
//    }, "000809", "0000", String.valueOf(MyApplication.mApplicationChannel));
  }
  
  private static void doYFPay()
  {
//    mjBilling.pay(getSerial(), mYfPayCode, mYfPrice);
  }
  
  private void doZZInit(Context paramContext)
  {
//    MaySDK.getInstance().initStart((Activity)paramContext, "50083", "1336", "de414240ea6843649a2bc18876b2bf13", String.valueOf(MyApplication.mApplicationChannel), new OnPayListener()
//    {
//      public void onFinish(int paramAnonymousInt, Object paramAnonymousObject) {}
//    });
  }
  
  private static void doZZPay()
  {
//    MyLogger.i(TAG, "doZZPay");
//    showToast("zz，ing..", MyContanst.DEBUG);
//    MaySDK.getInstance().onGo(m_Context, mZZPointID, mZZPrice, String.valueOf(MyApplication.mApplicationChannel), false, new OnPayListener()
//    {
//      public void onFinish(int paramAnonymousInt, Object paramAnonymousObject)
//      {
//        if (paramAnonymousInt == 200)
//        {
//          MyUtil.showToast("zz succeed", MyContanst.DEBUG);
//          Lotuseed.onEvent("ZZ_Succeed");
//          return;
//        }
//        MyUtil.showToast("zz error: " + paramAnonymousInt, MyContanst.DEBUG);
//      }
//    });
  }
  
  public static void exit(Context paramContext)
  {
    getInstance().doExit(paramContext);
  }
  
  public static void exitGame(Context paramContext) {}
  
  public static MyUtil getInstance()
  {
    if (m_Instance == null) {
      m_Instance = new MyUtil();
    }
    return m_Instance;
  }
  
  private static String getSerial()
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    for (;;)
    {
      if (i >= 8) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(localRandom.nextInt(10));
      i += 1;
    }
  }
  
  public static void init(Context paramContext)
  {
    getInstance().doinit(paramContext);
  }
  
  public static void init(Context paramContext, Handler paramHandler)
  {
    payHandler = paramHandler;
    getInstance().doinit(paramContext);
  }
  
  private void onEventStatistical(Context paramContext, String paramString)
  {
//    Lotuseed.onEvent(paramString);
//    TCAgent.onEvent(paramContext, paramString);
  }
  
  private static void onEventStatistical(Context paramContext, String paramString, HashMap<String, String> paramHashMap)
  {
//    Lotuseed.onEvent(paramString, paramHashMap);
//    TCAgent.onEvent(paramContext, paramString, paramString, paramHashMap);
  }
  
  public static void onPayEvent(Boolean paramBoolean)
  {
    paramBoolean.booleanValue();
  }
  
  public static void pause(Context paramContext)
  {
    getInstance().dopause(paramContext);
  }
  
  public static void payDlg(Context paramContext, String paramString1, String paramString2)
  {
    new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setNegativeButton("退出", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        MyUtil.onPayEvent(Boolean.valueOf(false));
      }
    }).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyUtil.onPayEvent(Boolean.valueOf(true));
      }
    }).create().show();
  }
  
  private void paySdk(String paramString)
  {
//    m_Context.runOnUiThread(new Runnable()
//    {
//      public void run()
//      {
//        try
//        {
//          MyUtil.access$7();
//          MyUtil.myHandler.sendEmptyMessageDelayed(1, 500L);
//          return;
//        }
//        catch (Exception localException)
//        {
//          for (;;)
//          {
//            localException.printStackTrace();
//          }
//        }
//      }
//    });
  }
  
  public static void resume(Context paramContext)
  {
    getInstance().doresume(paramContext);
  }
  
  public static void showToast(final String paramString, boolean paramBoolean)
  {
//    m_Context.runOnUiThread(new Runnable()
//    {
//      public void run()
//      {
//        if (this.val$debug) {
//          Toast.makeText(MyUtil.m_Context, paramString, 0).show();
//        }
//      }
//    });
  }
  
  public void doDMInit(Context paramContext)
  {
//    MU2tw.getInstance().init((Activity)paramContext, "936unp39p9c4wi8458vtjxxD", String.valueOf(MyApplication.mApplicationChannel));
  }
  
  public void doExit(Context paramContext) {}
  
  public void doPay(Context paramContext, String paramString)
  {
    m_Context = (Activity)paramContext;
    payInfo(paramString);
//    MyLogger.i("doPay", "propID: " + this.propID + ",mCHANNELID: " + MyApplication.mApplicationChannel);
//    Lotuseed.onEvent("RequestPay");
    paySdk(paramString);
  }
  
  public void doTYinit(Context paramContext)
  {
//    Payment.getInstance().init(paramContext, mTyBillingListener, "A278", String.valueOf(MyApplication.mApplicationChannel), "hzqy");
  }
  
  public void doWYInit(Context paramContext)
  {
//    WYZFPay.initPaySDK(paramContext, "20197132", String.valueOf(MyApplication.mApplicationChannel), new InitListener()
//    {
//      public void onInit(InitResult paramAnonymousInitResult)
//      {
//        paramAnonymousInitResult = InitResult.SUCCESS;
//      }
//    });
  }
  
  public void dodestory(Context paramContext)
  {
//    mPzPay.unregister();
    if (myHandler != null) {
      myHandler.removeCallbacksAndMessages(null);
    }
  }
  
  public void doinit(Context paramContext)
  {
    m_Context = (Activity)paramContext;
//    MyLogger.i(TAG, "doinit");
//    MyLogger.i(TAG, "LOTUS_CHANNEL:" + String.valueOf(MyApplication.mApplicationChannel));
//    Lotuseed.init(m_Context);
//    Lotuseed.startWithAppKey(MyContanst.LOTUSEED_KEY, String.valueOf(MyApplication.mApplicationChannel));
//    Lotuseed.onCrashLog();
//    Lotuseed.updateOnlineConfig();
//    try
//    {
//      doPzInit(paramContext);
//    }
//    catch (Exception localException6)
//    {
//      try
//      {
//        doZZInit(paramContext);
//      }
//      catch (Exception e)
//      {
//        try
//        {
//          doSyInit(paramContext);
//        }
//        catch (Exception localException6)
//        {
//          try
//          {
//            doDMInit(paramContext);
//          }
//          catch (Exception localException6)
//          {
//            try
//            {
//              doWYInit(paramContext);
//            }
//            catch (Exception localException6)
//            {
//              try
//              {
//                doYFInit(paramContext);
//              }
//              catch (Exception localException6)
//              {
//                try
//                {
//                  for (;;)
//                  {
//                    doTYinit(paramContext);
//                    Lotuseed.onEvent("EnterGame");
//                    return;
//                    localException1 = localException1;
//                    localException1.printStackTrace();
//                    continue;
//                    localException2 = localException2;
//                    localException2.printStackTrace();
//                    continue;
//                    localException3 = localException3;
//                    localException3.printStackTrace();
//                    continue;
//                    localException4 = localException4;
//                    localException4.printStackTrace();
//                    continue;
//                    localException5 = localException5;
//                    localException5.printStackTrace();
//                    continue;
//                    localException6 = localException6;
//                    localException6.printStackTrace();
//                  }
//                }
//                catch (Exception paramContext)
//                {
//                  for (;;)
//                  {
//                    paramContext.printStackTrace();
//                  }
//                }
//              }
//            }
//          }
//        }
//      }
//    }
  }
  
  public void dopause(Context paramContext)
  {
//    Lotuseed.onPause(paramContext);
  }
  
  public void doresume(Context paramContext)
  {
//    Lotuseed.onResume(paramContext);
  }
  
  public void payInfo(String paramString)
  {
	mCporderid = UUID.randomUUID().toString();
    if (paramString.equals("1"))
    {
      mPzPrice = 10;
      mZZPrice = "2000";
      this.mDESC = "极品大美女";
      mTitle = "极品大美女";
      this.propID = "00";
      payPoint = 1;
      this.bPNEPop = true;
      mZZPointID = "21708";
      mTKPrice = 1000;
      mTKPointID = "0105";
      mYLPrice = 1000;
      mYLGoodID = "P170405LQV5";
      mJmChild = "001005";
      this.mYJPrice = 2000;
      mKqPid = 249;
      mDmGid = "8qwxokgj9vqApaiebr49v8B4";
      mYfPayCode = "000809005";
      mYfPrice = "2000";
      mTYPayCode = "A27800";
      mTYPrice = "2000";
    }
//    while(true)
//    {
      
//      return;
      if (paramString.equals("2"))
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "马上就可以看到她真正的秘密花园了，可不能半途而废！快点磨枪啊！立即回复30点体力。";
        mTitle = "体力购买";
        this.propID = "01";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21689";
        mTKPrice = 1000;
        mTKPointID = "0102";
        mYLPrice = 1000;
        mYLGoodID = "P170405W0I2";
        mJmChild = "001006";
        this.mYJPrice = 2000;
        mKqPid = 246;
        mDmGid = "3nhe4av7hf22j2fbjm1dvm1w";
        mYfPayCode = "000809002";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
      else if (paramString.equals("3"))
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "马上闯入她的秘密花园，窥视她的秘密，展现你的魅力。";
        mTitle = "暴力闯入";
        this.propID = "02";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21690";
        mTKPrice = 1000;
        mTKPointID = "0103";
        mYLPrice = 1000;
        mYLGoodID = "P170405U1L3";
        mJmChild = "001005";
        this.mYJPrice = 2000;
        mKqPid = 247;
        mDmGid = "yhdpk80t21Dbtghe2oni9bp0";
        mYfPayCode = "000809003";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
      else if (paramString.equals("4"))
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "不用点道具怎么能把她调教的服服帖帖~";
        mTitle = "调教大礼包";
        this.propID = "03";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21691";
        mTKPrice = 1000;
        mTKPointID = "0104";
        mYLPrice = 1000;
        mYLGoodID = "P1704053V44";
        mJmChild = "001005";
        this.mYJPrice = 2000;
        mKqPid = 248;
        mDmGid = "bq65oB0l7CBa0xabaipdA0Ej";
        mYfPayCode = "000809004";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
      else if (paramString.equals("5"))
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "解禁她~窥视她~调教她~你越兴奋她给你的惊喜越多哦~";
        mTitle = "解锁美女";
        this.propID = "04";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21688";
        mTKPrice = 1000;
        mTKPointID = "0101";
        mYLPrice = 1000;
        mYLGoodID = "P170405XBD1";
        mJmChild = "001005";
        this.mYJPrice = 2000;
        mKqPid = 245;
        mDmGid = "cl77u04rrdEe7wcjahn7B116";
        mYfPayCode = "000809001";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
      else if (paramString.equals("6"))
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "立即解锁所有美女，开启隐藏美女房间~";
        mTitle = "解锁全部美女";
        this.propID = "05";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21687";
        mTKPrice = 1000;
        mTKPointID = "0101";
        mYLPrice = 2000;
        mYLGoodID = "P170405BOM0";
        mJmChild = "001006";
        this.mYJPrice = 2000;
        mKqPid = 244;
        mDmGid = "cl77u04rrdEe7wcjahn7B116";
        mYfPayCode = "000809000";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
      else
      {
        mPzPrice = 10;
        mZZPrice = "2000";
        this.mDESC = "马上就可以看到她真正的秘密花园了，可不能半途而废！快点磨枪啊！立即回复30点体力。";
        mTitle = "体力购买";
        this.propID = "01";
        payPoint = 1;
        this.bPNEPop = true;
        mZZPointID = "21689";
        mTKPrice = 1000;
        mTKPointID = "0102";
        mYLPrice = 1000;
        mYLGoodID = "P170405W0I2";
        mJmChild = "001005";
        this.mYJPrice = 2000;
        mKqPid = 246;
        mDmGid = "3nhe4av7hf22j2fbjm1dvm1w";
        mYfPayCode = "000809002";
        mYfPrice = "2000";
        mTYPayCode = "A27800";
        mTYPrice = "2000";
      }
  }
}
