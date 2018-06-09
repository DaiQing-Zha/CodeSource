package org.cocos2dx.cpp;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.main.MoSDK;
import com.oblong.sung.Call;
import com.oblong.sung.OnBack;
import com.platform.config.GameConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;
import com.utils.SystemTool;
import com.yq.yyf.dialog.DialogButtonListener;
import com.yq.yyf.dialog.PayDialogUtil;
import com.yq.yyf.utils.LogUtils;
import com.yq.yyf.utils.Utils;

@SuppressLint("NewApi")
public class AppActivity extends Cocos2dxActivity
{
  private static boolean chargingFlag;
  private static boolean issimk;
  public static AppActivity mContext;
  private static Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (AppActivity.issimk);
      switch (paramAnonymousMessage.what)
      {
      case 2:
      case 3:
      default:
        return;
      case 0:
        AppActivity.payMoney();
        return;
      case 1:
        AppActivity.payMoney();
        return;
      case 4:
      }
      AppActivity.payMoney();
    }
  };
  private static Toast toast;

  public static int getIsAppClear()
  {
    return 1;
  }

  public static int getisGiftBt()
  {
    return 2;
  }

  public static int getisReliveBt()
  {
    return 2;
  }

  public static int getisStartBt()
  {
    return 2;
  }

  public static int haveSimCard()
  {
    if (issimk)
      return 1;
    return 2;
  }

  public static void pay(int paramInt)
  {
    Log.e("list", "type = " + paramInt);
    Message localMessage = new Message();
    localMessage.what = paramInt;
    mHandler.sendMessage(localMessage);
  }

//  public static void pays()
//  {
//    if (issimk)
//    {
//      LogUtils.I("payAY()");
//      AppActivity.pays();
//    }
//  }

  public static void payMoney()
  {
	  MoSDK.doSDKForce(mContext);
//	if (GameConfig.verify_state == 1) {
//		AppActivity.pays();
//	}else {
//	    PayDialogUtil.getInstance().showPayDialog(mContext, new DialogButtonListener()
//	    {
//	      public void cancel()
//	      {
//	      }
//
//	      public void sure()
//	      {
//	        AppActivity.pays();
//	      }
//	    });
//	}
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    issimk = Utils.readSIMCard(this);
//    if (issimk)
//      new Handler(mContext.getMainLooper()).post(new Runnable()
//      {
//        public void run()
//        {
//          LogUtils.I("initAY()");
//          Call.getInstance().initStart(AppActivity.mContext, "50369", "2264", "e905dd4e695f11e8918e525400892fb7", "baobei", new OnBack()
//          {
//            public void Result(int paramAnonymous2Int, Object paramAnonymous2Object)
//            {
//              Log.e("初始化", "状态：" + paramAnonymous2Int + "==" + paramAnonymous2Object);
//            }
//          });
//        }
//      });
    
    /**
	* 设置组件化的Log开关
	* 参数: boolean 默认为false，如需查看LOG设置为true
	*/
	UMConfigure.setLogEnabled(true);
	/**
	* 设置日志加密
	* 参数：boolean 默认为false（不加密）
	*/
	UMConfigure.setEncryptEnabled(true);
	MobclickAgent.setScenarioType(this, EScenarioType.E_UM_NORMAL);
	MoSDK.getInstance().initSDK(this);
  }

  public Cocos2dxGLSurfaceView onCreateView()
  {
    Cocos2dxGLSurfaceView localCocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
    localCocos2dxGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 8);
    return localCocos2dxGLSurfaceView;
  }

  protected void onPause()
  {
    super.onPause();
    SystemTool.UmengAgentOnResume(this);
  }

  protected void onResume()
  {
    super.onResume();
    SystemTool.UmengAgentOnPause(this);
  }
}