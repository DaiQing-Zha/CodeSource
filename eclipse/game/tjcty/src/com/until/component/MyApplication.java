package com.until.component;

import com.pay.sdk.alan.application.AlanApplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

public class MyApplication extends AlanApplication
{
  public static int mApplicationChannel;
  
  private int getChannel(Context paramContext)
  {
    int i = 91488002;
    try
    {
      int j = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getInt("PZ_CNO");
      i = j;
      return j;
    }
    catch (PackageManager.NameNotFoundException e)
    {
      e.printStackTrace();
    }
    return i;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
  }
  
  public void onCreate()
  {
    super.onCreate();
    mApplicationChannel = getChannel(this);
  }
}
